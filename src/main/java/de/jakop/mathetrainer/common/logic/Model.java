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

import java.util.List;

import com.google.common.collect.Lists;

public class Model {

	private final List<Exercise> exercises;
	private Exercise currentExercise;
	private long correct;
	private long totalSeconds;

	public Model() {
		exercises = Lists.newArrayList();
	}

	public Exercise getCurrentExercise() {
		return currentExercise;
	}

	void setCurrentExercise(final Exercise currentExercise) {
		this.currentExercise = currentExercise;
	}

	void addExercise(final Exercise exercise) {
		exercises.add(exercise);
		totalSeconds += exercise.getSolutionTimeInSeconds();
		if (exercise.isCorrect()) {
			correct++;
		}
	}

	public long getExercisesCount() {
		return exercises.size();
	}

	public String getStatistics() {
		final int count = exercises.size();
		final double percentage = (double) correct / (double) count * 100;
		return String.format("%d Aufgaben bearbeitet, %d korrekt (%f)%%, %d:%02d:%02d%n", count, correct, percentage, totalSeconds / 3600, totalSeconds % 3600 / 60, totalSeconds % 60);
	}

}
