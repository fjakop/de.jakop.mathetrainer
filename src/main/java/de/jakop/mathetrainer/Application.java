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

import javax.swing.WindowConstants;

import de.jakop.mathetrainer.configuration.Configuration;
import de.jakop.mathetrainer.logic.ExerciseGenerator;
import de.jakop.mathetrainer.logic.Model;
import de.jakop.mathetrainer.ui.ApplicationFrame;

public class Application {

	public Application() {
		final Configuration configuration = new Configuration();
		final ExerciseGenerator generator = new ExerciseGenerator(configuration);

		final ApplicationFrame applicationFrame = new ApplicationFrame(configuration, generator, new Model());
		applicationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		applicationFrame.pack();
		applicationFrame.setSize(600, 700);
		applicationFrame.setVisible(true);

		//		controller.nextExercise();
		//		final CliInput input = new CliInput(controller);
		//		input.addObserver(inputObserver);
	}

	public static void main(final String[] args) {
		new Application();
	}
}
