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
import oo.simplegraph.api.Edge;
import oo.simplegraph.api.NavigableGraph;
import oo.simplegraph.api.Node;
import oo.simplegraph.api.StructuredGraph;

/**
 * 
 * @author Kapralov Sergey
 * @param <T>
 * @param <ND>
 * @param <ED> 
 */
class NgFromStructureInference<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements NavigableGraph.Inference<T, ND, ED> {
    private final StructuredGraph<T, ND, ED> structuredGraph;

    public NgFromStructureInference(StructuredGraph<T, ND, ED> structuredGraph) {
        this.structuredGraph = structuredGraph;
    }
    
    @Override
    public final NavigableGraph<T, ND, ED> graph() {
        return new NgFromEdges<T, ND, ED>(structuredGraph.edges().toList());
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.structuredGraph);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NgFromStructureInference<?, ?, ?> other = (NgFromStructureInference<?, ?, ?>) obj;
        if (!Objects.equals(this.structuredGraph, other.structuredGraph)) {
            return false;
        }
        return true;
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
public class NgFromStructure<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> extends NgInferred<T, ND, ED> implements NavigableGraph<T, ND, ED> {
    public NgFromStructure(StructuredGraph<T, ND, ED> sg) {
        super(
                new NgFromStructureInference<>(sg)
        );
    }
}
