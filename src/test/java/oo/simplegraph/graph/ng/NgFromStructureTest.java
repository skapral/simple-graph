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
package oo.simplegraph.graph.ng;

import javaslang.collection.HashSet;
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.graph.sg.SgEmpty;
import oo.simplegraph.graph.sg.SgNoMeta;
import oo.simplegraph.graph.sg.SgSimple;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class NgFromStructureTest {

    @Test
    public final void producesNavigableGraphFromSetOfEdges() {
        NavigableGraph ngraph = new NgFromStructure(
                new SgNoMeta<>(
                        HashSet.of(
                                new NValue<>(1),
                                new NValue<>(2),
                                new NValue<>(3)
                        ),
                        HashSet.of(
                                new EDirected<>(
                                        new NValue<>(1),
                                        new NValue<>(2)
                                ),
                                new EDirected<>(
                                        new NValue<>(1),
                                        new NValue<>(3)
                                ),
                                new EDirected<>(
                                        new NValue<>(2),
                                        new NValue<>(3)
                                )
                        )
                )
        );
        assertThat(
                ngraph.adjacentEdges(
                        new NValue(1)
                )
        ).containsOnly(
                new EDirected<>(
                        new NValue<>(1),
                        new NValue<>(2)
                ),
                new EDirected<>(
                        new NValue<>(1),
                        new NValue<>(3)
                )
        );
    }
}
