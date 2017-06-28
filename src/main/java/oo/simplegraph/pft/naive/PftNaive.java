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
package oo.simplegraph.pft.naive;

import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.api.Edge;
import oo.simplegraph.api.Node;
import oo.simplegraph.api.PathFindingTask;
import oo.simplegraph.api.NavigableGraph;

/**
 *
 * @author Kapralov Sergey
 */
public class PftNaive<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements PathFindingTask<T, ND, ED> {
    private final NavigableGraph<T, ND, ED> graph;
    
    public PftNaive(NavigableGraph<T, ND, ED> graph) {
        this.graph = graph;
    }
    
    @Override
    public final Option<List<ED>> path(ND nodeStart, ND nodeEnd) {
        if (nodeStart.equals(nodeEnd)) {
            return Option.of(List.empty());
        }
        List<PathChunk<T, ND, ED>> pathChunks = List.of(new PcEmpty<>(nodeStart));
        while (!pathChunks.isEmpty()) {
            pathChunks = pathChunks.flatMap(pc -> {
                ND tail = pc.tail();
                return List.ofAll(graph.edges(tail))
                        .map(pc::advance)
                        .filter(Option::isDefined)
                        .map(Option::get);
            });
            List<PathChunk<T, ND, ED>> potentialResults = pathChunks.filter(pc -> pc.tail().equals(nodeEnd));
            if(!potentialResults.isEmpty()) {
                return Option.of(
                    potentialResults.get(0).path()
                );
            }
        }
        return Option.none();
    }
}
