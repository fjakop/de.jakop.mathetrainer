/*
 *  (c) tolina GmbH, 2016
 */
package de.jakop.mathetrainer.ui;

import static de.jakop.mathetrainer.configuration.Operation.ADDITION;
import static de.jakop.mathetrainer.configuration.Operation.MULTIPLICATION;
import static de.jakop.mathetrainer.configuration.Operation.SUBTRACTION;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import de.jakop.mathetrainer.configuration.Configuration;
import de.jakop.mathetrainer.configuration.Operation;

public class ApplicationFrame extends JFrame {

	private final class OperationSelectionItemListener implements ItemListener {
		private final Operation operation;

		public OperationSelectionItemListener(final Operation operation) {
			this.operation = operation;
		}

		@Override
		public void itemStateChanged(final ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				configuration.enableOperation(operation);
			} else {
				configuration.disableOperation(operation);
			}
		}
	}

	private static final long serialVersionUID = 3549123919945092574L;
	private final Configuration configuration;
	private final JLabel outputField;

	public ApplicationFrame(final Configuration configuration) {
		this.configuration = configuration;
		outputField = new JLabel("out");

		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = createDefaultGbC();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 0.0;
		getContentPane().add(createConfiguration(), gbc);
		gbc.gridy++;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		getContentPane().add(createHistory(), gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		gbc.weighty = 0.0;
		getContentPane().add(createInput(), gbc);

	}

	private Component createInput() {
		final JPanel inputPanel = new JPanel(new GridBagLayout());
		final GridBagConstraints gbc = createDefaultGbC();

		final JTextField inputField = new JTextField("in");
		final JButton submit = new JButton("Ok");

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
		return inputPanel;
	}

	private Component createHistory() {
		return new JTextArea("History");
	}

	private Component createConfiguration() {
		final JPanel configurationPanel = new JPanel(new GridBagLayout());
		final GridBagConstraints gbc = createDefaultGbC();

		final JCheckBox addition = new JCheckBox("Addition");
		final JCheckBox subtraction = new JCheckBox("Subtraktion");
		final JCheckBox multiplication = new JCheckBox("Multiplikation");
		final JLabel dummy = new JLabel();

		configurationPanel.add(addition, gbc);
		gbc.gridx++;
		configurationPanel.add(subtraction, gbc);
		gbc.gridx++;
		configurationPanel.add(multiplication, gbc);
		gbc.gridx++;
		gbc.weightx = 1.0;
		configurationPanel.add(dummy, gbc);

		addition.addItemListener(new OperationSelectionItemListener(ADDITION));
		subtraction.addItemListener(new OperationSelectionItemListener(SUBTRACTION));
		multiplication.addItemListener(new OperationSelectionItemListener(MULTIPLICATION));

		return configurationPanel;
	}

	private GridBagConstraints createDefaultGbC() {
		return new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);
	}


}
