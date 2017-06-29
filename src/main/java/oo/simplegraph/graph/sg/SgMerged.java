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

class SgMergedInference<T, N extends Node<T>, E extends Edge<T, N, E>> implements StructuredGraph.Inference<T, N, E> {
    private final Set<StructuredGraph<T, N, E>> graphs;

    public SgMergedInference(Set<StructuredGraph<T, N, E>> graphs) {
        this.graphs = graphs;
    }
    
    @Override
    public final StructuredGraph<T, N, E> graph() {
        HashSet<N> nodes = graphs
                .foldLeft(HashSet.empty(), (hs, g) -> hs.addAll(g.nodes()));
        HashSet<E> edges = graphs
                .foldLeft(HashSet.empty(), (hs, g) -> hs.addAll(g.edges()));
        nodes = nodes.addAll(edges.flatMap(Edge::nodes));
        return new SgSimple<>(nodes, edges);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.graphs);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SgMergedInference) {
            final SgMergedInference<?, ?, ?> other = (SgMergedInference<?, ?, ?>) obj;
            return Objects.equals(this.graphs, other.graphs);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "SgMergedInference{" + "graphs=" + graphs + '}';
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SgMerged<T, N extends Node<T>, E extends Edge<T, N, E>> extends SgInferred<T, N, E> implements StructuredGraph<T, N, E> {
    public SgMerged(StructuredGraph<T, N, E>... graphs) {
        this(HashSet.of(graphs));
    }
    
    public SgMerged(Set<StructuredGraph<T, N, E>> graphs) {
        super(new SgMergedInference<>(graphs));
    }
}
