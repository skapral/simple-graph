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
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;



class SgWithNodesAndEdgesInference<T> implements StructuredGraph.Inference<T> {
    private final StructuredGraph<T> sg;
    private final Set<Node<T>> nodes;
    private final Set<Edge<T>> edges;

    public SgWithNodesAndEdgesInference(StructuredGraph<T> sg, Set<Node<T>> nodes, Set<Edge<T>> edges) {
        this.sg = sg;
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    public StructuredGraph<T> graph() {
        return new StructuredGraph<T>() {
            @Override
            public Set<Node<T>> nodes() {
                return sg.nodes().addAll(nodes).addAll(edges.flatMap(Edge::nodes));
            }

            @Override
            public Set<Edge<T>> edges() {
                return sg.edges().addAll(edges);
            }
        };
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(this.sg, this.nodes, this.edges);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SgWithNodesAndEdgesInference) {
            final SgWithNodesAndEdgesInference other = (SgWithNodesAndEdgesInference) obj;
            return Objects.equals(this.sg, other.sg) &&
                   Objects.equals(this.nodes, other.nodes) &&
                   Objects.equals(this.edges, other.edges);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "SgWithNodesAndEdgesInference{" + "sg=" + sg + ", nodes=" + nodes + ", edges=" + edges + '}';
    }
}


/**
 *
 * @author Kapralov Sergey
 */
public class SgWithNodesAndEdges<T> extends SgInferred<T> implements StructuredGraph<T> {
    public SgWithNodesAndEdges(StructuredGraph<T> sg, Set<Node<T>> nodes, Set<Edge<T>> edges) {
        super(new SgWithNodesAndEdgesInference<>(sg, nodes, edges));
    }
}
