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
package oo.simplegraph.graph.cg.dijkstra;

import oo.simplegraph.edge.EBiDirected;
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.graph.cpgwm.CpgwmBiDirectedGraph;
import oo.simplegraph.graph.cpgwm.CpgwmDirectedGraph;
import oo.simplegraph.graph.ng.NgFromStructure;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class DgImplementationTest {
    @Test
    public void generatesDijkstraTree() {
        StructuredGraph<String, Integer> sg = new CpgwmBiDirectedGraph<String, Integer>()
                // add nodes
            .withNode(new NValue<>("a"),  0)
            .withNode(new NValue<>("a1"), 0)
            .withNode(new NValue<>("a2"), 0)
            .withNode(new NValue<>("b"), 0)
            .withEdge(new NValue<>("a"), new NValue<>("a1"), 1)
            .withEdge(new NValue<>("a"), new NValue<>("a2"), 1)
            .withEdge(new NValue<>("a1"), new NValue<>("b"), 1)
            .withEdge(new NValue<>("a2"), new NValue<>("b"), 1)
            .result();
        
        assertThat(
                new DgImplementation(
                        new NgFromStructure(sg),
                        new NValue<>("a") 
                ).result().edges()
        ).containsOnly(
                new EBiDirected<>(new NValue<>("a"), new NValue<>("a1")),
                new EBiDirected<>(new NValue<>("a1"), new NValue<>("b")),
                new EBiDirected<>(new NValue<>("a"), new NValue<>("a2"))
        );
    }
    
    @Test
    public void canMergeCorrectlyPathWeights() {
        StructuredGraph<String, Integer> sg = new CpgwmDirectedGraph<String, Integer>()
                // add nodes
            .withNode(new NValue<>("a1"),  0)
            .withNode(new NValue<>("a2"), 0)
            .withNode(new NValue<>("a3"), 0)
            .withEdge(new NValue<>("a1"), new NValue<>("a2"), 1)
            .withEdge(new NValue<>("a2"), new NValue<>("a3"), 1)
            .withEdge(new NValue<>("a1"), new NValue<>("a3"), 2)
            .result();
        
        assertThat(
                new DgImplementation(
                        new NgFromStructure(sg),
                        new NValue<>("a1") 
                ).result().edges()
        ).containsOnly(
                new EDirected<>(new NValue<>("a1"), new NValue<>("a2")),
                new EDirected<>(new NValue<>("a2"), new NValue<>("a3"))
        );
    }
}
