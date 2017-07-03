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
package oo.simplegraph.graph.cgwm;

import oo.simplegraph.edge.EDirected;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class CgwmSimpleTest {
    @Test
    public void controlsConsistencyOfPassedNodesAndEdges() {
        assertThatThrownBy(() -> {
            ConstructedGraphWithMeta<Integer, Integer> cgwm = new CgwmSimple<Integer, Integer>()
                .withNode(new NValue<>(1), 1)
                .withNode(new NValue<>(2), 2)
                .withEdge(
                        new EDirected<>(
                                new NValue<>(1),
                                new NValue<>(2)
                        ),
                        3
                )
                .withEdge(
                        new EDirected<>(
                                new NValue<>(2),
                                new NValue<>(3)
                        ),
                        4
                )
                .withEdge(
                        new EDirected<>(
                                new NValue<>(3),
                                new NValue<>(1)
                        ),
                        3
                );
        }).isInstanceOf(RuntimeException.class);
    }
}
