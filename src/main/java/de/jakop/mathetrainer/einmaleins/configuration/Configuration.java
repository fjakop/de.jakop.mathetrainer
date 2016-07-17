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

package de.jakop.mathetrainer.einmaleins.configuration;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Configuration extends de.jakop.mathetrainer.common.configuration.Configuration {

	private static final String EINMALEINS_ROWS = "einmaleins.rows";

	private final Set<Integer> sequences = Sets.newHashSet();

	public Configuration() {
		setOperandCount(2);
		setOperandMaxValue(12);
		final String rowsParameter = System.getProperty(EINMALEINS_ROWS, "1,2,3");
		final List<String> rowList = Splitter.on(",").trimResults().splitToList(rowsParameter);
		final List<Integer> rows = rowList.stream().mapToInt(Integer::valueOf).boxed().collect(Collectors.toList());
		sequences.addAll(rows);
	}

	public void setSequence(final int sequence, final boolean enabled) {
		if (enabled) {
			sequences.add(sequence);
		} else {
			sequences.remove(sequence);
		}
	}

	public Collection<Integer> getSequences() {
		return ImmutableSet.copyOf(sequences);
	}

}
