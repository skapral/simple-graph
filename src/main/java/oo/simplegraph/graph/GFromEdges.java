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
package oo.simplegraph.graph;

import oo.simplegraph.api.Node;
import java.util.Objects;
import javaslang.collection.HashMap;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.collection.Set;
import oo.simplegraph.api.Edge;
import oo.simplegraph.api.Graph;

/**
 * 
 * @author Kapralov Sergey
 * @param <T>
 * @param <ND>
 * @param <ED> 
 */
class SimpleGraphInference<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements Graph.Inference<T, ND, ED> {
    private final List<ED> edges;

    public SimpleGraphInference(List<ED> edges) {
        this.edges = edges;
    }

    @Override
    public final Graph<T, ND, ED> graph() {
        Map<Node<T>, Set<ED>> mappedEdges = HashMap.empty();
        for (ED edge : edges) {
            List<ND> nodes = edge.nodes();

            for (ND node : nodes) { //TODO: eliminate this loop when javaslang 2.1.0 is released
                Set<ED> oldList = mappedEdges.get(node).getOrElse(HashSet.empty());
                Set<ED> newList = oldList.add(edge);
                mappedEdges = mappedEdges.put(node, newList);
            }
        }
        return new GSimple<>(mappedEdges);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.edges);
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
        final SimpleGraphInference<T, ND, ED> other = (SimpleGraphInference<T, ND, ED>) obj;
        if (!Objects.equals(this.edges, other.edges)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "SimpleGraphInference{" + "edges=" + edges + '}';
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class GFromEdges<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> extends GInferred<T, ND, ED> implements Graph<T, ND, ED> {
    public GFromEdges(ED... edges) {
        this(List.of(edges));
    }

    public GFromEdges(List<ED> edges) {
        super(
            new SimpleGraphInference<>(edges)
        );
    }
}
