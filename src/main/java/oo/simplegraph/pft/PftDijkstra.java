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

import java.util.Objects;
import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.cg.dijkstra.DgImplementation;
import oo.simplegraph.node.Node;
import oo.simplegraph.graph.ng.NavigableGraph;
import oo.simplegraph.graph.ng.NgFromStructure;
import oo.simplegraph.graph.sg.SgFromConstructedGraph;

/**
 * Dijkstra shortest path task
 * 
 * @author Kapralov Sergey
 */
public class PftDijkstra<T> implements PathFindingTask<T> {
    private final NavigableGraph<T, Integer> graph;

    public PftDijkstra(NavigableGraph<T, Integer> graph) {
        this.graph = graph;
    }

    @Override
    public final Option<List<Edge<T>>> path(Node<T> nodeStart, Node<T> nodeEnd) {
        NavigableGraph<T, Integer> dijkstraGraph = new NgFromStructure<>(
                new SgFromConstructedGraph<>(
                        new DgImplementation<T>(
                                graph,
                                nodeStart
                        )
                )
        );

        // TODO: get rid of PftNaive here
        return new PftNaive<>(dijkstraGraph).path(nodeStart, nodeEnd); 
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.graph);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof PftDijkstra) {
            final PftDijkstra<?> other = (PftDijkstra<?>) obj;
            return Objects.equals(this.graph, other.graph);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "PftDijkstra{" + "graph=" + graph + '}';
    }
}
