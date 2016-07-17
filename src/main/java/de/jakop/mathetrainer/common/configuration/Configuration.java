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

package de.jakop.mathetrainer.common.configuration;

import java.awt.Font;

public class Configuration {

	public static final float FONT_SIZE = 32.0f;
	public static final Font HISTORY_FONT = new Font("Courier", Font.PLAIN, 14);

	private static final String OPERAND_COUNT = "operand.count";
	private static final String OPERAND_MAXVALUE = "operand.maxvalue";

	private int operandMaxvalue;
	private int operandCount;

	public final int getOperandCount() {
		if (operandCount == 0) {
			operandCount = Integer.valueOf(System.getProperty(OPERAND_COUNT, "2"));
		}
		return operandCount;
	}

	public final void setOperandCount(final int value) {
		operandCount = value;
	}

	public final int getOperandMaxValue() {
		if (operandMaxvalue == 0) {
			operandMaxvalue = Integer.valueOf(System.getProperty(OPERAND_MAXVALUE, "10"));
		}
		return operandMaxvalue;
	}

	public final void setOperandMaxValue(final int value) {
		operandMaxvalue = value;
	}

}
