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

package de.jakop.mathetrainer.einmaleins.logic;

import com.google.code.mathparser.MathParser;
import com.google.code.mathparser.MathParserFactory;
import com.google.code.mathparser.parser.calculation.Result;
import com.google.common.base.Supplier;

import de.jakop.mathetrainer.common.configuration.Operation;
import de.jakop.mathetrainer.common.logic.Exercise;
import de.jakop.mathetrainer.einmaleins.configuration.Configuration;

public class ExerciseGenerator implements Supplier<Exercise> {

	private final Configuration configuration;
	private final MathParser mathParser;

	public ExerciseGenerator(final Configuration configuration) {
		this.configuration = configuration;
		mathParser = MathParserFactory.create();
	}

	@Override
	public Exercise get() {
		// generate as much operator-operand-terms as configured
		final StringBuilder text = new StringBuilder();
		// first operand without operator
		final int firstOperand = getOperand1();
		text.append(firstOperand);

		for (int operandIndex = 1; operandIndex < configuration.getOperandCount(); operandIndex++) {
			// assemble exercise text
			text.append(Operation.MULTIPLICATION.getSymbol());
			text.append(getOperand2());
		}

		// assemble exercise
		final String expression = text.toString();
		final Result result = mathParser.calculate(expression);
		return new Exercise(expression, result.doubleValue().intValue());
	}

	private int getOperand1() {
		return (int) (Math.random() * configuration.getOperandMaxValue() + 1);
	}

	private int getOperand2() {
		final int[] values = configuration.getSequences().stream().mapToInt(i -> i).toArray();
		final int index = (int) (Math.random() * values.length);

		return values[index];
	}
}
