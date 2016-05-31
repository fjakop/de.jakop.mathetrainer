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

import javax.swing.JFrame;

import de.jakop.mathetrainer.configuration.Configuration;
import de.jakop.mathetrainer.logic.Controller;
import de.jakop.mathetrainer.logic.Model;

public class ApplicationFrame extends JFrame {

	private static final long serialVersionUID = 3549123919945092574L;

	public ApplicationFrame(//
			final Configuration configuration, final Model model, //
			final Controller controller, //
			final InputPanel inputPanel, final HistoryPanel historyPanel, final TimeBarPanel timeBarPanel) {

		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0.0;
		getContentPane().add(new ConfigurationPanel(configuration), gbc);
		gbc.gridy++;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(historyPanel, gbc);

		gbc.weighty = 0.0;
		gbc.gridy++;
		getContentPane().add(timeBarPanel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		getContentPane().add(inputPanel, gbc);

		getRootPane().setDefaultButton(inputPanel.getSubmitButton());
	}

}
