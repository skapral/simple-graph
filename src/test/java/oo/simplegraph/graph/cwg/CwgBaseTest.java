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
package oo.simplegraph.graph.cwg;

import oo.simplegraph.edge.EBiDirected;
import oo.simplegraph.edge.EWeighted;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.NValue;
import oo.simplegraph.node.NWeighted;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class CwgBaseTest {

    @Test
    public void generatesWeightedGraph() {
        StructuredGraph sg = new CwgBiDirected()
                .withNode(new NValue(1))
                .withNode(new NValue(2), 5)
                .withNode(new NValue(3))
                .withEdge(new NValue(1), new NValue(2))
                .withEdge(new NValue(2), new NValue(3), 5)
                .withEdge(new NValue(3), new NValue(1))
                .result();
        assertThat(
                sg.nodes()
        ).containsOnly(
                new NWeighted<>(
                        new NValue<>(1),
                        0
                ),
                new NWeighted<>(
                        new NValue<>(2),
                        5
                ),
                new NWeighted<>(
                        new NValue<>(3),
                        0
                )
        );

        assertThat(
                sg.edges()
        ).containsOnly(
                new EWeighted<>(
                        new EBiDirected<>(
                                new NWeighted<>(
                                        new NValue<>(1),
                                        0
                                ),
                                new NWeighted<>(
                                        new NValue<>(2),
                                        5
                                )
                        ),
                        1
                ),
                new EWeighted<>(
                        new EBiDirected<>(
                                new NWeighted<>(
                                        new NValue<>(2),
                                        5
                                ),
                                new NWeighted<>(
                                        new NValue<>(3),
                                        0
                                )
                        ),
                        5
                ),
                new EWeighted<>(
                        new EBiDirected<>(
                                new NWeighted<>(
                                        new NValue<>(3),
                                        0
                                ),
                                new NWeighted<>(
                                        new NValue<>(1),
                                        0
                                )
                        ),
                        1
                )
        );
    }
}
