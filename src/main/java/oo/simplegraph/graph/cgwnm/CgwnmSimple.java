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
package oo.simplegraph.graph.cgwnm;

import oo.simplegraph.graph.cgwm.*;
import java.util.Objects;
import javaslang.collection.HashMap;
import javaslang.collection.HashSet;
import javaslang.collection.Map;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.edge.meta.EmStatic;
import oo.simplegraph.graph.sg.SgNoMeta;
import oo.simplegraph.graph.sg.SgSimple;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.Node;
import oo.simplegraph.node.meta.NmStatic;

/**
 *
 * @author Kapralov Sergey
 */
public class CgwnmSimple<T> implements ConstructedGraphWithNoMeta<T> {
    private final Set<Node<T>> nodes;
    private final Set<Edge<T>> edges;

    public CgwnmSimple() {
        this(
                HashSet.empty(),
                HashSet.empty()
        );
    }

    public CgwnmSimple(Set<Node<T>> nodes, Set<Edge<T>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
    
    @Override
    public final ConstructedGraphWithNoMeta<T> withNode(Node<T> node) {
        return new CgwnmSimple<>(
                nodes.add(node), 
                edges
        );
    }

    @Override
    public final ConstructedGraphWithNoMeta<T> withEdge(Edge<T> edge) {
        if(nodes.containsAll(edge.nodes())) {
            return new CgwnmSimple<>(
                    nodes, 
                    edges.add(edge)
            );
        } else {
            throw new RuntimeException(
                String.format("attempt to add edge with inexisting nodes: %e", edge)
            );
        }
    }

    @Override
    public final StructuredGraph<T, Void> result() {
        return new SgNoMeta<>(
                nodes,
                edges
        );
    }
    

    @Override
    public final int hashCode() {
        return Objects.hash(this.nodes, this.edges);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof CgwnmSimple) {
            final CgwnmSimple other = (CgwnmSimple) obj;
            return Objects.equals(this.nodes, other.nodes) &&
                   Objects.equals(this.edges, other.edges);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "CdgValidatingIntegrity{" + "nodes=" + nodes + ", edges=" + edges + '}';
    }
}
