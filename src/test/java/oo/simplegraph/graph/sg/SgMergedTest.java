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
package oo.simplegraph.graph.sg;

import javaslang.collection.HashSet;
import oo.simplegraph.edge.EBiDirected;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class SgMergedTest {
    @Test
    public final void mergesSeveralGraphsTogether() {
        StructuredGraph graph1 = new SgSimple(
                HashSet.of(new NValue<>(1), new NValue<>(2)),
                HashSet.of(
                        new EBiDirected<>(
                                new NValue<>(1),
                                new NValue<>(2)
                        )
                )
        );
        StructuredGraph graph2 = new SgSimple(
                HashSet.of(new NValue<>(2), new NValue<>(3)),
                HashSet.of(
                        new EBiDirected<>(
                                new NValue<>(2),
                                new NValue<>(3)
                        )
                )
        );
        StructuredGraph graph3 = new SgSimple(
                HashSet.of(new NValue<>(1), new NValue<>(3)),
                HashSet.of(
                        new EBiDirected<>(
                                new NValue<>(1),
                                new NValue<>(3)
                        )
                )
        );
        assertThat(
                new SgMerged(
                        graph1,
                        graph2,
                        graph3
                ).nodes()
        ).containsOnly(
                new NValue<>(1),
                new NValue<>(2),
                new NValue<>(3)
        );
        assertThat(
                new SgMerged(
                        graph1,
                        graph2,
                        graph3
                ).edges()
        ).containsOnly(
                new EBiDirected<>(
                        new NValue<>(1),
                        new NValue<>(2)
                ),
                new EBiDirected<>(
                        new NValue<>(2),
                        new NValue<>(3)
                ),
                new EBiDirected<>(
                        new NValue<>(1),
                        new NValue<>(3)
                )
        );
    }
}
