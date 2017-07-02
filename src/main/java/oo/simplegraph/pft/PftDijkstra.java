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
package oo.simplegraph.pft;

import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;
import oo.simplegraph.pft.PathFindingTask;
import oo.simplegraph.graph.ng.NavigableGraph;

/**
 *
 * @author Kapralov Sergey
 */
public class PftDijkstra<T, M> implements PathFindingTask<T, M> {
    private final NavigableGraph<T, M> graph;

    public PftDijkstra(NavigableGraph<T, M> graph) {
        this.graph = graph;
    }

    @Override
    public final Option<List<Edge<T>>> path(Node<T> nodeStart, Node<T> nodeEnd) {
        if (nodeStart.equals(nodeEnd)) {
            return Option.of(List.empty());
        }
        throw new UnsupportedOperationException();
    }
}
