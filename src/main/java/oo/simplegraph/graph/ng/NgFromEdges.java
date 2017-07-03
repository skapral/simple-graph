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
 * THE SOFTWARE IS PROVIDEdge<Node<T>, ?> "AS IS", WITHOUT WARRANTY OF ANY KINode<T>, EXPRESS OR
 * IMPLIEdge<Node<T>, ?>, INCLUDING BUT NOT LIMITEdge<Node<T>, ?> TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE ANode<T> NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package oo.simplegraph.graph.ng;

import oo.simplegraph.node.Node;
import java.util.Objects;
import javaslang.collection.HashMap;
import javaslang.collection.HashSet;
import javaslang.collection.Map;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.edge.meta.EdgeMeta;
import oo.simplegraph.edge.meta.EmEmpty;
import oo.simplegraph.node.meta.NmEmpty;
import oo.simplegraph.node.meta.NodeMeta;

class NgFromEdgesInference<T, M> implements NavigableGraph.Inference<T, M> {

    private final Set<Edge<T>> edges;
    private final NodeMeta<T, M> nodeMeta;
    private final EdgeMeta<T, M> edgeMeta;

    public NgFromEdgesInference(Set<Edge<T>> edges, NodeMeta<T, M> nodeMeta, EdgeMeta<T, M> edgeMeta) {
        this.edges = edges;
        this.nodeMeta = nodeMeta;
        this.edgeMeta = edgeMeta;
    }

    @Override
    public final NavigableGraph<T, M> graph() {
        Map<Node<T>, Set<Edge<T>>> mappedEdges = HashMap.empty();
        for (Edge<T> edge : edges) {
            Set<Node<T>> nodes = edge.startingNodes();

            for (Node<T> node : nodes) { //TODO: eliminate this loop when javaslang 2.1.0 is released
                Set<Edge<T>> oldList = mappedEdges.get(node).getOrElse(HashSet.empty());
                Set<Edge<T>> newList = oldList.add(edge);
                mappedEdges = mappedEdges.put(node, newList);
            }
        }
        return new NgSimple<>(mappedEdges, nodeMeta, edgeMeta);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(edges);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NgFromEdgesInference) {
            final NgFromEdgesInference other = (NgFromEdgesInference) obj;
            return Objects.equals(this.edges, other.edges);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NgFromEdgesInference{" + "edges=" + edges + '}';
    }
}

/**
 * Navigable graph from edges.
 * 
 * @author Kapralov Sergey
 * @param <T>
 * @param <M>
 */
public class NgFromEdges<T, M> extends NgInferred<T, M> implements NavigableGraph<T, M> {

    public NgFromEdges(Edge<T>... edges) {
        this(HashSet.of(edges));
    }

    public NgFromEdges(Set<Edge<T>> edges) {
        this(
                edges,
                new NmEmpty<>(),
                new EmEmpty<>()
        );
    }
    
    public NgFromEdges(Set<Edge<T>> edges, NodeMeta<T, M> nodeMeta, EdgeMeta<T, M> edgeMeta) {
        super(new NgFromEdgesInference<>(edges, nodeMeta, edgeMeta));
    }
}
