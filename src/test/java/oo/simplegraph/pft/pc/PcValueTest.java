/*
 * The MIT License
 *
 * Copyright 2017 Kapralov Sergey.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package oo.simplegraph.pft.pc;

import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.edge.EBiDirected;
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class PcValueTest {

    @Test
    public final void canAdvanceInRightDirection() {
        PathChunk pc = new PcValue(
                new NValue<>(1),
                new NValue<>(2),
                List.of(
                        new EDirected<>(
                                new NValue<>(1),
                                new NValue<>(2)
                        )
                )
        );
        Option<PathChunk> pcAdvancedOption = pc.advance(
                new EDirected(
                        new NValue<>(2),
                        new NValue<>(3)
                )
        );
        assertThat(
                pcAdvancedOption.isEmpty()
        ).isFalse();
        PathChunk pcAdvanced = pcAdvancedOption.get();
        assertThat(
                pcAdvanced.head()
        ).isEqualTo(
                new NValue<>(1)
        );
        assertThat(
                pcAdvanced.tail()
        ).isEqualTo(
                new NValue<>(3)
        );
        assertThat(
                pcAdvanced.path()
        ).containsExactly(
                new EDirected<>(
                        new NValue<>(1),
                        new NValue<>(2)
                ),
                new EDirected(
                        new NValue<>(2),
                        new NValue<>(3)
                )
        );
    }

    @Test
    public final void cannotAdvanceInWrongDirection() {
        PathChunk pc = new PcValue(
                new NValue<>(1),
                new NValue<>(2),
                List.of(
                        new EDirected<>(
                                new NValue<>(1),
                                new NValue<>(2)
                        )
                )
        );
        Option<PathChunk> pcAdvanced = pc.advance(
                new EDirected(
                        new NValue<>(3),
                        new NValue<>(2)
                )
        );
        assertThat(
                pcAdvanced
        ).isEqualTo(
                Option.none()
        );
    }

    @Test
    @Ignore // TODO: need to think more about this case
    public final void canEliminateLoopsWithBiDirectedEdges() {
        PathChunk pc = new PcValue(
                new NValue<>(1),
                new NValue<>(3),
                List.of(
                        new EBiDirected<>(
                                new NValue<>(1),
                                new NValue<>(2)
                        ),
                        new EBiDirected<>(
                                new NValue<>(2),
                                new NValue<>(3)
                        )
                )
        );
        Option<PathChunk> pcAdvanced = pc.advance(
                new EBiDirected(
                        new NValue<>(3),
                        new NValue<>(1)
                )
        );
        assertThat(
                pcAdvanced
        ).isEqualTo(
                Option.none()
        );
    }

    @Test
    @Ignore // TODO: need to think more about this case
    public final void canEliminateLoopsWithDirectedEdges() {
        PathChunk pc = new PcValue(
                new NValue<>(1),
                new NValue<>(3),
                List.of(
                        new EDirected<>(
                                new NValue<>(1),
                                new NValue<>(2)
                        ),
                        new EDirected<>(
                                new NValue<>(2),
                                new NValue<>(3)
                        )
                )
        );
        Option<PathChunk> pcAdvanced = pc.advance(
                new EDirected(
                        new NValue<>(3),
                        new NValue<>(1)
                )
        );
        assertThat(
                pcAdvanced
        ).isEqualTo(
                Option.none()
        );
    }
}
