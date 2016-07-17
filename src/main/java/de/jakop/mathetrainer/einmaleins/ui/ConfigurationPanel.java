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

package de.jakop.mathetrainer.einmaleins.ui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import de.jakop.mathetrainer.einmaleins.configuration.Configuration;

public class ConfigurationPanel extends JPanel {

	private static final long serialVersionUID = -168145045627837738L;

	public ConfigurationPanel(final Configuration configuration) {

		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

		add(createOperandsPanel(configuration), gbc);
	}

	private JPanel createOperandsPanel(final Configuration configuration) {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

		final JSpinner maxOperandValue = addLabeledSpinner(panel, "max. Wert Operand", new SpinnerNumberModel(configuration.getOperandMaxValue(), 2, 50, 1), gbc);
		maxOperandValue.getModel().addChangeListener(e -> {
			configuration.setOperandMaxValue((int) maxOperandValue.getValue());
		});

		gbc.gridx++;
		for (int i = 1; i <= 12; i++) {
			final int sequence = i;
			final JCheckBox check = new JCheckBox(String.valueOf(sequence));
			check.setSelected(configuration.getSequences().contains(sequence));
			panel.add(check, gbc);
			gbc.gridx++;
			check.addItemListener(e -> {
				configuration.setSequence(sequence, check.isSelected());
			});
		}

		fillWithDummy(panel, gbc);
		return panel;
	}

	private void fillWithDummy(final Container container, final GridBagConstraints gbc) {
		final double weightx = gbc.weightx;
		final int fill = gbc.fill;
		final JLabel dummy = new JLabel();
		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		container.add(dummy, gbc);
		gbc.weightx = weightx;
		gbc.fill = fill;
	}

	private JSpinner addLabeledSpinner(final Container container, final String label, final SpinnerModel model, final GridBagConstraints gbc) {
		final JLabel l = new JLabel(label);
		container.add(l, gbc);
		gbc.gridx++;
		final JSpinner spinner = new JSpinner(model);
		l.setLabelFor(spinner);
		container.add(spinner, gbc);

		return spinner;
	}

}
