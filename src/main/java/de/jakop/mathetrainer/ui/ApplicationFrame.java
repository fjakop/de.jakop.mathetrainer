/*******************************************************************************
 * The MIT License (MIT)
 * Copyright (c) 2016, Frank Jakop
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/

package de.jakop.mathetrainer.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.common.base.Stopwatch;

import de.jakop.mathetrainer.configuration.Configuration;
import de.jakop.mathetrainer.logic.Exercise;
import de.jakop.mathetrainer.logic.ExerciseGenerator;
import de.jakop.mathetrainer.logic.Model;

public class ApplicationFrame extends JFrame {


	// TODO --> Configuration
	private static final float FONT_SIZE = 32.0f;
	private static final Font HISTORY_FONT = new Font("Courier", Font.PLAIN, 14);

	private static final long serialVersionUID = 3549123919945092574L;
	private final ExerciseGenerator generator;
	private final JLabel outputField;
	private final Model model;
	private final JTextArea history;
	private final Stopwatch stopwatch;
	private final Configuration configuration;

	public ApplicationFrame(final Configuration configuration, final ExerciseGenerator generator, final Model model) {
		this.configuration = configuration;
		this.generator = generator;
		this.model = model;
		stopwatch = Stopwatch.createUnstarted();

		outputField = new JLabel();
		history = new JTextArea();
		history.setEditable(false);
		history.setFont(HISTORY_FONT);
		final JScrollPane historyScroll = new JScrollPane(history);

		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = createDefaultGbC();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0.0;
		getContentPane().add(new ConfigurationPanel(configuration), gbc);
		gbc.gridy++;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(historyScroll, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		gbc.weighty = 0.0;
		getContentPane().add(createInput(), gbc);

		nextExercise();
	}

	private Component createInput() {
		final JPanel inputPanel = new JPanel(new GridBagLayout());
		final GridBagConstraints gbc = createDefaultGbC();

		final JTextField inputField = new JTextField();

		final JButton submit = new JButton("OK");
		getRootPane().setDefaultButton(submit);

		submit.addActionListener(e -> {
			solve(inputField.getText());
			inputField.setText(null);
			inputField.requestFocus();
		});

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		inputPanel.add(outputField, gbc);

		inputField.setColumns(8);
		gbc.gridx++;
		gbc.weightx = 0.0;
		inputPanel.add(inputField, gbc);

		gbc.gridx++;
		gbc.weightx = 0.0;
		inputPanel.add(submit, gbc);

		inputField.setFont(inputField.getFont().deriveFont(FONT_SIZE));
		outputField.setFont(outputField.getFont().deriveFont(FONT_SIZE));
		submit.setFont(submit.getFont().deriveFont(FONT_SIZE));


		return inputPanel;
	}

	private void nextExercise() {
		model.setCurrentExercise(generator.get());
		outputField.setText(model.getCurrentExercise().getText());
		stopwatch.start();
	}

	private void solve(final String solution) {
		stopwatch.stop();
		final Exercise exercise = model.getCurrentExercise();
		exercise.setSolution(solution);
		exercise.setSolutionTimeInSeconds(stopwatch.elapsed(TimeUnit.SECONDS));
		stopwatch.reset();
		recordHistory(exercise);
		nextExercise();
	}

	private void recordHistory(final Exercise exercise) {
		final int maxWidth = configuration.getOperandCount() * (int) (Math.log10(configuration.getOperandMaxValue()) + 1) + configuration.getOperandCount();
		final String correctness = exercise.isCorrect() ? "Korrekt!" : "Leider falsch.";
		model.addExercise(exercise);
		history.append(String.format("%-" + maxWidth + "s (%3ds) %s%n", exercise.getText(), exercise.getSolutionTimeInSeconds(), correctness));
		if (model.getExercisesCount() % 10 == 0) {
			history.append(model.getStatistics());
		}
		history.setCaretPosition(history.getDocument().getLength());
	}

	private GridBagConstraints createDefaultGbC() {
		return new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
	}

}
