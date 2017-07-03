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
import oo.simplegraph.edge.meta.EdgeMeta;
import oo.simplegraph.edge.meta.EmEmpty;
import oo.simplegraph.node.meta.NmEmpty;
import oo.simplegraph.node.meta.NodeMeta;

/**
 * A basic implementation of {@link NavigableGraph}
 * 
 * @author Kapralov Sergey
 */
public class NgSimple<T, M> implements NavigableGraph<T, M> {
    private final Map<Node<T>, Set<Edge<T>>> edges;
    private final NodeMeta<T, M> nodeMeta;
    private final EdgeMeta<T, M> edgeMeta;

    public NgSimple(Map<Node<T>, Set<Edge<T>>> edges) {
        this(
                edges,
                new NmEmpty<>(),
                new EmEmpty<>()
        );
    }

    public NgSimple(Map<Node<T>, Set<Edge<T>>> edges, NodeMeta<T, M> nodeMeta, EdgeMeta<T, M> edgeMeta) {
        this.edges = edges;
        this.nodeMeta = nodeMeta;
        this.edgeMeta = edgeMeta;
    }
    
    @Override
    public final List<Edge<T>> adjacentEdges(Node<T> node) {
        return edges.get(node)
                .map(Set::toList)
                .getOrElse(List.empty());
    }

    @Override
    public final NodeMeta<T, M> nodeMeta() {
        return nodeMeta;
    }

    @Override
    public final EdgeMeta<T, M> edgeMeta() {
        return edgeMeta;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(edges, nodeMeta, edgeMeta);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NgSimple) {
            final NgSimple other = (NgSimple) obj;
            return Objects.equals(this.edges, other.edges) &&
                   Objects.equals(this.nodeMeta, other.nodeMeta) &&
                   Objects.equals(this.edgeMeta, other.edgeMeta);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NgSimple{" + "edges=" + edges + ", nodeMeta=" + nodeMeta + ", edgeMeta=" + edgeMeta + '}';
    }
}
