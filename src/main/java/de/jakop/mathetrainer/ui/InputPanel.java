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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.common.eventbus.Subscribe;

import de.jakop.mathetrainer.configuration.Configuration;
import de.jakop.mathetrainer.logic.Controller;
import de.jakop.mathetrainer.logic.NewExerciseEvent;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = -9127376372672751029L;

	private final JLabel outputField;
	private final JButton submit;


	public InputPanel(final Controller controller) {

		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

		outputField = new JLabel("foo");
		final JTextField inputField = new JTextField();

		submit = new JButton("OK");

		getSubmitButton().addActionListener(e -> {
			controller.solve(inputField.getText());
			inputField.setText(null);
			inputField.requestFocus();
		});

		gbc.weightx = 1.0;
		add(outputField, gbc);

		inputField.setColumns(8);
		gbc.gridx++;
		gbc.weightx = 0.0;
		add(inputField, gbc);

		gbc.gridx++;
		gbc.weightx = 0.0;
		add(getSubmitButton(), gbc);

		inputField.setFont(inputField.getFont().deriveFont(Configuration.FONT_SIZE));
		outputField.setFont(outputField.getFont().deriveFont(Configuration.FONT_SIZE));
		getSubmitButton().setFont(getSubmitButton().getFont().deriveFont(Configuration.FONT_SIZE));

	}

	public JButton getSubmitButton() {
		return submit;
	}

	@Subscribe
	public void newExercise(final NewExerciseEvent event) {
		outputField.setText(event.getExercise().getText());
	}
}
