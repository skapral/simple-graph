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
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class SgWithNodesAndEdgesTest {
    @Test
    public void addsNodestoStructuredGraph() {
        StructuredGraph sg = new SgWithNodes(
                new SgEmpty(),
                new NValue<>("1")
        );
        StructuredGraph sgWithNodes = new SgWithNodes(
                sg,
                new NValue<>("2"),
                new NValue<>("3")
        );
        assertThat(
                sgWithNodes.nodes()
        ).containsOnly(
                new NValue<>("1"),
                new NValue<>("2"),
                new NValue<>("3")
        );
    }
    
    @Test
    public final void addsEdgesToGraphStructure() {
        StructuredGraph sg = new SgWithNodesAndEdges(
                new SgEmpty(),
                HashSet.of(
                        new NValue<>("1"),
                        new NValue<>("2"),
                        new NValue<>("3")
                ),
                HashSet.of(
                        new EDirected<>(
                                new NValue<>("1"),
                                new NValue<>("2")
                        )
                )
        );
        StructuredGraph sgWithEdges = new SgWithEdges(
                sg,
                new EDirected<>(
                        new NValue<>("2"),
                        new NValue<>("3")
                )
        );
        assertThat(
                sgWithEdges.edges()
        ).containsOnly(
                new EDirected<>(
                        new NValue<>("2"),
                        new NValue<>("3")
                ),
                new EDirected<>(
                        new NValue<>("1"),
                        new NValue<>("2")
                )
        );
    }

    @Test
    public final void mergesNodesIfTheyAreNotInGraph() {
        StructuredGraph sg = new SgWithNodesAndEdges(
                new SgEmpty(),
                HashSet.of(
                        new NValue<>("1"),
                        new NValue<>("2")
                ),
                HashSet.of(
                        new EDirected<>(
                                new NValue<>("1"),
                                new NValue<>("2")
                        )
                )
        );
        StructuredGraph sgWithEdges = new SgWithEdges(
                sg,
                new EDirected<>(
                        new NValue<>("2"),
                        new NValue<>("3")
                )
        );
        assertThat(
                sgWithEdges.edges()
        ).containsOnly(
                new EDirected<>(
                        new NValue<>("1"),
                        new NValue<>("2")
                ),
                new EDirected<>(
                        new NValue<>("2"),
                        new NValue<>("3")
                )
        );
    }

    @Test
    public final void missingNodesAreAddedFromEdges() {
        StructuredGraph sg = new SgWithNodesAndEdges(
                new SgEmpty(),
                HashSet.of(
                        new NValue<>("1"),
                        new NValue<>("2")
                ),
                HashSet.of(
                        new EDirected<>(
                                new NValue<>("1"),
                                new NValue<>("2")
                        )
                )
        );
        StructuredGraph sgWithEdges = new SgWithEdges(
                sg,
                new EDirected<>(
                        new NValue<>("2"),
                        new NValue<>("3")
                )
        );
        assertThat(
                sgWithEdges.nodes()
        ).containsOnly(
                new NValue<>("1"),
                new NValue<>("2"),
                new NValue<>("3")
        );
    }

    @Test
    public void addsEdgesAfterNewNodesAdded() {
        StructuredGraph sg = new SgWithNodes(
                new SgEmpty(),
                new NValue(1),
                new NValue(2),
                new NValue(3)
        );
        assertThat(
                new SgWithEdges(
                        sg,
                        new EDirected(
                                new NValue(1),
                                new NValue(2)
                        )
                ).edges()
        ).containsOnly(
                new EDirected(
                        new NValue(1),
                        new NValue(2)
                )
        );
    }
}
