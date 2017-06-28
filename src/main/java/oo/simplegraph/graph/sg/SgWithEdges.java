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

import java.util.Objects;
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;

/**
 * 
 * @author Kapralov Sergey
 * @param <T>
 * @param <ND>
 * @param <ED> 
 */
class SgWithEdgesInference<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements StructuredGraph.Inference<T, ND, ED> {
    private final StructuredGraph<T, ND, ED> sg;
    private final Set<ED> edges;

    public SgWithEdgesInference(StructuredGraph<T, ND, ED> sg, Set<ED> edges) {
        this.sg = sg;
        this.edges = edges;
    }
    
    @Override
    public final StructuredGraph<T, ND, ED> graph() {
        return new SgSimple<>(sg.nodes().addAll(edges.flatMap(Edge::nodes)), sg.edges().addAll(edges));
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.sg);
        hash = 59 * hash + Objects.hashCode(this.edges);
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
        final SgWithEdgesInference<?, ?, ?> other = (SgWithEdgesInference<?, ?, ?>) obj;
        if (!Objects.equals(this.sg, other.sg)) {
            return false;
        }
        if (!Objects.equals(this.edges, other.edges)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "SgWithEdgesInference{" + "sg=" + sg + ", edges=" + edges + '}';
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SgWithEdges<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> extends SgInferred<T, ND, ED> implements StructuredGraph<T, ND, ED> {
    public SgWithEdges(StructuredGraph<T, ND, ED> sg, Set<ED> edges) {
        super(
            new SgWithEdgesInference<>(
                sg, edges
            )
        );
    }
    
    public SgWithEdges(StructuredGraph<T, ND, ED> sg, ED... edges) {
        this(sg, HashSet.of(edges));
    }
}
