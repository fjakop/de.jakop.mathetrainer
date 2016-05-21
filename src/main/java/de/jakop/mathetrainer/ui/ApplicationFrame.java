/*
 *  (c) tolina GmbH, 2016
 */
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
		history.append(String.format("%-" + maxWidth + "s (%3ds) %s%n", exercise.getText(), exercise.getSolutionTimeInSeconds(), correctness));
		history.setCaretPosition(history.getDocument().getLength());
	}

	private GridBagConstraints createDefaultGbC() {
		return new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
	}

}
