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

package de.jakop.mathetrainer.calculation;

import javax.swing.JPanel;

import com.google.common.base.Supplier;

import de.jakop.mathetrainer.calculation.configuration.Configuration;
import de.jakop.mathetrainer.calculation.logic.ExerciseGenerator;
import de.jakop.mathetrainer.calculation.ui.ConfigurationPanel;
import de.jakop.mathetrainer.common.logic.Exercise;

public class ObjectFactory implements de.jakop.mathetrainer.common.ObjectFactory {

	private de.jakop.mathetrainer.calculation.configuration.Configuration configuration;
	private Supplier<Exercise> exerciseGenerator;
	private ConfigurationPanel configurationPanel;

	@Override
	public Configuration getConfiguration() {
		if (configuration == null) {
			configuration = new Configuration();
		}
		return configuration;
	}

	@Override
	public Supplier<Exercise> getExerciseGenerator() {
		if (exerciseGenerator == null) {
			exerciseGenerator = new ExerciseGenerator(getConfiguration());

		}
		return exerciseGenerator;
	}

	@Override
	public JPanel getConfigurationPanel() {
		if (configurationPanel == null) {
			configurationPanel = new ConfigurationPanel(getConfiguration());
		}
		return configurationPanel;
	}
}
