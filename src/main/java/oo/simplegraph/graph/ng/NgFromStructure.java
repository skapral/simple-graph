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

import java.util.Objects;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;
import oo.simplegraph.graph.sg.StructuredGraph;

/**
 * 
 * @author Kapralov Sergey
 * @param <T>
 * @param <ND>
 * @param <ED> 
 */
class NgFromStructureInference<ND extends Node<?>, ED extends Edge<ND, ED>> implements NavigableGraph.Inference<ND, ED> {
    private final StructuredGraph<ND, ED> structuredGraph;

    public NgFromStructureInference(StructuredGraph<ND, ED> structuredGraph) {
        this.structuredGraph = structuredGraph;
    }
    
    @Override
    public final NavigableGraph<ND, ED> graph() {
        return new NgFromEdges<ND, ED>(structuredGraph.edges());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(structuredGraph);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NgFromStructureInference) {
            final NgFromStructureInference<?, ?> other = (NgFromStructureInference<?, ?>) obj;
            return Objects.equals(this.structuredGraph, other.structuredGraph);
        }else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NgFromStructureInference{" + "structuredGraph=" + structuredGraph + '}';
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class NgFromStructure<ND extends Node<?>, ED extends Edge<ND, ED>> extends NgInferred<ND, ED> implements NavigableGraph<ND, ED> {
    public NgFromStructure(StructuredGraph<ND, ED> sg) {
        super(
                new NgFromStructureInference<>(sg)
        );
    }
}
