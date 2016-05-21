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
 */

package de.jakop.mathetrainer.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.google.common.eventbus.Subscribe;

import de.jakop.mathetrainer.configuration.Configuration;
import de.jakop.mathetrainer.logic.Exercise;
import de.jakop.mathetrainer.logic.SolutionEvent;
import de.jakop.mathetrainer.logic.StatisticsEvent;

public class HistoryPanel extends JScrollPane {

	private static final long serialVersionUID = 6901992614151043955L;

	private final JTextArea history;

	private final Configuration configuration;

	public HistoryPanel(final Configuration configuration) {
		this.configuration = configuration;
		history = new JTextArea();
		history.setEditable(false);
		history.setFont(Configuration.HISTORY_FONT);
		setViewportView(history);
	}

	@Subscribe
	public void solved(final SolutionEvent event) {
		final Exercise exercise = event.getExercise();
		final int maxWidth = configuration.getOperandCount() * (int) (Math.log10(configuration.getOperandMaxValue()) + 1) + configuration.getOperandCount();
		final String correctness = exercise.isCorrect() ? "Korrekt!" : "Leider falsch.";
		history.append(String.format("%-" + maxWidth + "s (%3ds) %s%n", exercise.getText(), exercise.getSolutionTimeInSeconds(), correctness));
		history.setCaretPosition(history.getDocument().getLength());
	}

	@Subscribe
	public void statistics(final StatisticsEvent event) {
		history.append(event.getText());
		history.setCaretPosition(history.getDocument().getLength());
	}


}
