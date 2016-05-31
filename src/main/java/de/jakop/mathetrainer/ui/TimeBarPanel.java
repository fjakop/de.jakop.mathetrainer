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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import de.jakop.mathetrainer.configuration.Configuration;

public class TimeBarPanel extends JPanel {

	private static final long serialVersionUID = -5556349924632311259L;

	private final Timer timer;
	private final long delay;
	private final Configuration configuration;
	private final JProgressBar progressBar;

	public TimeBarPanel(final Configuration configuration) {

		this.configuration = configuration;
		delay = 75;
		timer = new Timer(true);

		setLayout(new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);

		progressBar = new JProgressBar(0, 100);
		add(progressBar, gbc);
	}

	public void start() {
		timer.scheduleAtFixedRate(new TimerListener(delay, configuration.getSolutionTime(), progressBar), 0, delay);
	}

	public void stop() {
		timer.cancel();
	}

	public void reset() {
		progressBar.setValue(0);
	}

	private class TimerListener extends TimerTask {

		private final JProgressBar progressBar;
		private final long maxTimeMs;
		private final long delay;
		private long elapsedMs;

		public TimerListener(final long delay, final long maxTimeMs, final JProgressBar progressBar) {
			this.delay = delay;
			this.maxTimeMs = maxTimeMs;
			this.progressBar = progressBar;
		}

		@Override
		public void run() {
			elapsedMs += delay;
			progressBar.setValue((int) ((double) elapsedMs / (double) maxTimeMs * 100));
			if (elapsedMs >= maxTimeMs) {
				stop();
			}
		}
	}


}
