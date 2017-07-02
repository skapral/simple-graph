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
import oo.simplegraph.edge.meta.EdgeMeta;
import oo.simplegraph.node.Node;
import oo.simplegraph.node.meta.NodeMeta;

/**
 *
 * @author Kapralov Sergey
 */
public class SgSimple<T, M> implements StructuredGraph<T, M> {
    private final Set<Node<T>> nodes;
    private final Set<Edge<T>> edges;
    private final NodeMeta<T, M> nodeMeta;
    private final EdgeMeta<T, M> edgeMeta;
    
    public SgSimple(Set<Node<T>> nodes, Set<Edge<T>> edges, NodeMeta<T, M> nodeMeta, EdgeMeta<T, M> edgeMeta) {
        this.nodes = nodes;
        this.edges = edges;
        this.nodeMeta = nodeMeta;
        this.edgeMeta = edgeMeta;
    }

    @Override
    public final Set<Node<T>> nodes() {
        return nodes;
    }

    @Override
    public final Set<Edge<T>> edges() {
        return edges;
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
        return Objects.hash(
                nodes,
                edges,
                nodeMeta,
                edgeMeta);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SgSimple) {
            final SgSimple other = (SgSimple) obj;
            
            return Objects.equals(this.nodes, other.nodes) &&
                    Objects.equals(this.edges, other.edges) &&
                    Objects.equals(this.nodeMeta, other.nodeMeta) &&
                    Objects.equals(this.edgeMeta, other.edgeMeta);
        } else {
            return false;
        }
    }
    
    @Override
    public final String toString() {
        return "SgSimple{" + "nodes=" + nodes + ", edges=" + edges + ", nodeMeta=" + nodeMeta + ", edgeMeta=" + edgeMeta + '}';
    }
}
