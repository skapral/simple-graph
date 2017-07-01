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

/**
 *
 * @author Kapralov Sergey
 * @param <T>
 * @param <Node<T>>
 * @param <Edge<Node<T>, ?>>
 */
class NgFromEdgesInference<T> implements NavigableGraph.Inference<T> {

    private final Set<Edge<T>> edges;

    public NgFromEdgesInference(Set<Edge<T>> edges) {
        this.edges = edges;
    }

    @Override
    public final NavigableGraph<T> graph() {
        Map<Node<T>, Set<Edge<T>>> mappedEdges = HashMap.empty();
        for (Edge<T> edge : edges) {
            Set<Node<T>> nodes = edge.startingNodes();

            for (Node<T> node : nodes) { //TODO: eliminate this loop when javaslang 2.1.0 is released
                Set<Edge<T>> oldList = mappedEdges.get(node).getOrElse(HashSet.empty());
                Set<Edge<T>> newList = oldList.add(edge);
                mappedEdges = mappedEdges.put(node, newList);
            }
        }
        return new NgSimple<>(mappedEdges);
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
 *
 * @author Kapralov Sergey
 */
public class NgFromEdges<T> extends NgInferred<T> implements NavigableGraph<T> {

    public NgFromEdges(Edge<T>... edges) {
        this(HashSet.of(edges));
    }

    public NgFromEdges(Set<Edge<T>> edges) {
        super(new NgFromEdgesInference<>(edges));
    }
}
