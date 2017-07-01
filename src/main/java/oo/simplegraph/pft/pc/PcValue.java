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
package oo.simplegraph.pft.pc;

import java.util.Objects;
import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class PcValue<T> implements PathChunk<T> {
    private final Node<T> startNode;
    private final Node<T> endNode;
    private final List<Edge<T>> edges;

    public PcValue(Node<T> startNode, Node<T> endNode, List<Edge<T>> edges) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.edges = edges;
    }

    @Override
    public final Node<T> head() {
        return startNode;
    }

    @Override
    public final Node<T> tail() {
        return endNode;
    }

    @Override
    public final List<Edge<T>> path() {
        return edges;
    }

    @Override
    public final Option<PathChunk<T>> advance(Edge<T> edge) {
        if(edges.contains(edge)) {
            // We are in a loop. No sense in advancing further
            return Option.none();
        }
        
        return edge.follow(endNode).map(n -> new PcValue<>(startNode, n, edges.append(edge)));
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.startNode, this.endNode, this.edges);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof PcValue) {
            final PcValue other = (PcValue) obj;
            return Objects.equals(this.startNode, other.startNode) &&
                    Objects.equals(this.endNode, other.endNode) &&
                    Objects.equals(this.edges, other.edges);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "PcValue{" + "startNode=" + startNode + ", endNode=" + endNode + ", edges=" + edges + '}';
    }
}
