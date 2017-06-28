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

import oo.simplegraph.node.Node;
import java.util.Objects;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;

/**
 *
 * @author Kapralov Sergey
 */
public class NgSimple<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements NavigableGraph<T, ND, ED> {
    private final Map<Node<T>, Set<ED>> edges;

    public NgSimple(Map<Node<T>, Set<ED>> edges) {
        this.edges = edges;
    }
    
    @Override
    public final List<ED> edges(ND node) {
        return edges.get(node)
                .map(Set::toList)
                .getOrElse(List.empty());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.edges);
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
        final NgSimple<T, ND, ED> other = (NgSimple<T, ND, ED>) obj;
        if (!Objects.equals(this.edges, other.edges)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "NgSimple{" + "edges=" + edges + '}';
    }
}
