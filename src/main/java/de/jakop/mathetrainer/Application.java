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

package de.jakop.mathetrainer;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.google.common.base.Supplier;
import com.google.common.eventbus.EventBus;

import de.jakop.mathetrainer.common.ObjectFactory;
import de.jakop.mathetrainer.common.configuration.Configuration;
import de.jakop.mathetrainer.common.logic.Controller;
import de.jakop.mathetrainer.common.logic.Exercise;
import de.jakop.mathetrainer.common.logic.Model;
import de.jakop.mathetrainer.common.ui.ApplicationFrame;
import de.jakop.mathetrainer.common.ui.HistoryPanel;
import de.jakop.mathetrainer.common.ui.InputPanel;

public class Application {

	public Application() {
		final String mode = System.getProperty("mode", "calculation");
		final ObjectFactory factory;
		try {
			factory = (ObjectFactory) Class.forName("de.jakop.mathetrainer." + mode + ".ObjectFactory").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(String.format("Mode '%s' is unsupported", mode), e);
		}

		final Configuration configuration = factory.getConfiguration();
		final Supplier<Exercise> generator = factory.getExerciseGenerator();
		final Model model = new Model();
		final EventBus eventBus = new EventBus();
		final Controller controller = new Controller(model, generator, eventBus);

		final HistoryPanel historyPanel = new HistoryPanel(configuration);
		final InputPanel inputPanel = new InputPanel(controller);
		final JPanel configurationPanel = factory.getConfigurationPanel();
		final ApplicationFrame applicationFrame = new ApplicationFrame(configurationPanel, model, controller, inputPanel, historyPanel);

		eventBus.register(historyPanel);
		eventBus.register(inputPanel);

		controller.nextExercise();

		applicationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		applicationFrame.pack();
		applicationFrame.setSize(600, 700);
		applicationFrame.setVisible(true);
	}

	public static void main(final String[] args) throws Exception {
		new Application();
	}
}
