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

package de.jakop.mathetrainer.configuration;

import static de.jakop.mathetrainer.configuration.Operator.ADD;
import static de.jakop.mathetrainer.configuration.Operator.MULTIPLY;
import static de.jakop.mathetrainer.configuration.Operator.SUBTRACT;

import java.util.Collection;
import java.util.Collections;

import com.google.common.collect.Lists;

public class Configuration {

	private final Collection<Operator> modes;
	private int operandMaxvalue;
	private int operandCount;

	public Configuration() {
		modes = Lists.newArrayList(ADD, SUBTRACT, MULTIPLY);
	}

	public Collection<Operator> getOperationModes() {
		return Collections.unmodifiableCollection(modes);
	}

	public int getOperandCount() {
		if (operandCount == 0) {
			operandCount = Integer.valueOf(System.getProperty("operand.count", "2"));
		}
		return operandCount;
	}

	public int getMaxOperand() {
		if (operandMaxvalue == 0) {
			operandMaxvalue = Integer.valueOf(System.getProperty("operand.maxvalue", "10"));
		}
		return operandMaxvalue;
	}




}
