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

package de.jakop.mathetrainer.common.logic;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class Exercise {

	private final String text;
	private final int expected;

	private String solution;
	private final Stopwatch stopwatch;

	public Exercise(final String text, final int expected) {
		this.text = text;
		this.expected = expected;
		stopwatch = Stopwatch.createUnstarted();
	}

	public String getText() {
		return text;
	}

	public boolean isCorrect() {
		try {
			final int value = Integer.parseInt(solution);
			return value == expected;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(final String solution) {
		this.solution = solution.trim();
	}

	public long getSolutionTimeInSeconds() {
		return stopwatch.elapsed(TimeUnit.SECONDS);
	}

	public Stopwatch getStopwatch() {
		return stopwatch;
	}
}
