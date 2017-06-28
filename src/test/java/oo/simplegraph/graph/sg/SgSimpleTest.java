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
import javaslang.collection.Set;
import oo.simplegraph.edge.EBiDirected;
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.node.NUnique;
import oo.simplegraph.node.NValue;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Kapralov Sergey
 */
public class SgSimpleTest {
    @Test
    public final void basicEquality() {
        SgSimple sg = new SgSimple();
        
        assertThat(
                sg
        ).isEqualTo(
                sg
        );
        assertThat(
                sg
        ).isNotEqualTo(
                null
        );
        assertThat(
                sg
        ).isNotEqualTo(
                new Object()
        );
    }
    
    @Test
    public final void holdsNodes() {
        Set nodes = HashSet.of(
                new NUnique(),
                new NUnique(),
                new NUnique()
        );
        SgSimple sg = new SgSimple(nodes, HashSet.empty());
        assertThat(
                sg.nodes()
        ).isEqualTo(
                nodes
        );
    }
    
    @Test
    public final void holdsEdges() {
        Set edges = HashSet.of(
                new EDirected<>(
                        new NUnique(),
                        new NUnique()
                )
        );
        SgSimple sg = new SgSimple(HashSet.empty(), edges);
        assertThat(
                sg.edges()
        ).isEqualTo(
                edges
        );
    }
}
