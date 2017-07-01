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
package oo.simplegraph.graph.tg;

import oo.simplegraph.edge.EBiDirected;
import oo.simplegraph.graph.ng.NavigableGraph;
import oo.simplegraph.graph.ng.NgFromEdges;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class TgFromNavigableGraphTest {

    @Test
    public void initialTraversalHasOnlyOneNodeAndNoEdgesTraversed() {
        NavigableGraph graph = new NgFromEdges(
                new EBiDirected(
                        new NValue(0),
                        new NValue(1)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(2)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(3)
                )
        );

        assertThat(
                new TgFromNavigableGraph(graph, new NValue(0)).traverseNodes()
        ).containsOnly(
                new NValue(0)
        );
        assertThat(
                new TgFromNavigableGraph(graph, new NValue(0)).traversedEdges()
        ).isEmpty();
    }

    @Test
    public void nominalTraversal() {
        NavigableGraph graph = new NgFromEdges(
                new EBiDirected(
                        new NValue(0),
                        new NValue(1)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(2)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(3)
                )
        );
        TgFromNavigableGraph tg = new TgFromNavigableGraph(graph, new NValue(0));

        assertThat(
                tg.nextIteration().traverseNodes()
        ).containsOnly(
                new NValue(1),
                new NValue(2),
                new NValue(3)
        );
        assertThat(
                tg.nextIteration().traversedEdges()
        ).containsOnly(
                new EBiDirected(
                        new NValue(0),
                        new NValue(1)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(2)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(3)
                )
        );
    }

    @Test
    public void consequentTraversals() {
        NavigableGraph graph = new NgFromEdges(
                new EBiDirected(
                        new NValue(0),
                        new NValue(1)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(2)
                ),
                new EBiDirected(
                        new NValue(1),
                        new NValue(10)
                ),
                new EBiDirected(
                        new NValue(2),
                        new NValue(10)
                )
        );
        TgFromNavigableGraph tg = new TgFromNavigableGraph(graph, new NValue(0));

        assertThat(
                tg.nextIteration().nextIteration().traverseNodes()
        ).containsOnly(
                new NValue(10)
        );
        assertThat(
                tg.nextIteration().nextIteration().traversedEdges()
        ).containsOnly(
                new EBiDirected(
                        new NValue(0),
                        new NValue(1)
                ),
                new EBiDirected(
                        new NValue(0),
                        new NValue(2)
                ),
                new EBiDirected(
                        new NValue(1),
                        new NValue(10)
                ),
                new EBiDirected(
                        new NValue(2),
                        new NValue(10)
                )
        );
    }

}
