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

import com.google.common.base.Supplier;
import com.google.common.eventbus.EventBus;

public class Controller {

	private final Model model;
	private final Supplier<Exercise> generator;
	private final EventBus eventBus;

	public Controller(final Model model, final Supplier<Exercise> generator, final EventBus eventBus) {
		this.model = model;
		this.generator = generator;
		this.eventBus = eventBus;
	}

	public void solve(final String solution) {
		final Exercise exercise = model.getCurrentExercise();
		exercise.getStopwatch().stop();
		exercise.setSolution(solution);
		recordHistory(exercise);
		nextExercise();
	}

	public void nextExercise() {
		final Exercise exercise = generator.get();
		model.setCurrentExercise(exercise);
		eventBus.post(new NewExerciseEvent(exercise));
		exercise.getStopwatch().start();
	}

	public void recordHistory(final Exercise exercise) {
		model.addExercise(exercise);
		eventBus.post(new SolutionEvent(exercise));
		if (model.getExercisesCount() % 10 == 0) {
			eventBus.post(new StatisticsEvent(model.getStatistics()));
		}
	}
}
