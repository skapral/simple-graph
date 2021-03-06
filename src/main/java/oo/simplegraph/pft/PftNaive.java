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
import oo.simplegraph.pft.pc.PcEmpty;
import oo.simplegraph.pft.pc.PathChunk;
import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;
import oo.simplegraph.pft.PathFindingTask;
import oo.simplegraph.graph.ng.NavigableGraph;

/**
 * A naive implementation of {@link PathFindingTask}
 * 
 * @author Kapralov Sergey
 */
public class PftNaive<T> implements PathFindingTask<T> {
    private final NavigableGraph<T, ?> graph;
    
    public PftNaive(NavigableGraph<T, ?> graph) {
        this.graph = graph;
    }
    
    @Override
    public final Option<List<Edge<T>>> path(Node<T> nodeStart, Node<T> nodeEnd) {
        if (nodeStart.equals(nodeEnd) && !graph.adjacentEdges(nodeStart).isEmpty()) {
            return Option.of(List.empty());
        }
        List<PathChunk<T>> pathChunks = List.of(new PcEmpty<>(nodeStart));
        while (!pathChunks.isEmpty()) {
            pathChunks = pathChunks.flatMap(pc -> {
                Node<T> tail = pc.tail();
                return List.ofAll(graph.adjacentEdges(tail))
                        .map(pc::advance)
                        .filter(Option::isDefined)
                        .map(Option::get);
            });
            List<PathChunk<T>> potentialResults = pathChunks.filter(pc -> pc.tail().equals(nodeEnd));
            if(!potentialResults.isEmpty()) {
                return Option.of(
                    potentialResults.get(0).path()
                );
            }
        }
        return Option.none();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.graph);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof PftNaive) {
            final PftNaive other = (PftNaive) obj;
            return Objects.equals(this.graph, other.graph);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "PftNaive{" + "graph=" + graph + '}';
    }
}
