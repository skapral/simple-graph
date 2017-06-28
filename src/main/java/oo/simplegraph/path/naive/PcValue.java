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
package oo.simplegraph.path.naive;

import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.api.Edge;
import oo.simplegraph.api.Node;

/**
 *
 * @author Kapralov Sergey
 */
class PcValue<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements PathChunk<T, ND, ED> {
    private final ND startNode;
    private final ND endNode;
    private final List<ED> edges;

    public PcValue(ND startNode, ND endNode, List<ED> edges) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.edges = edges;
    }

    @Override
    public final ND head() {
        return startNode;
    }

    @Override
    public final ND tail() {
        return endNode;
    }

    @Override
    public final List<ED> path() {
        return edges;
    }

    @Override
    public final Option<PathChunk<T, ND, ED>> advance(ED edge) {
        if(edges.contains(edge)) {
            // We are in a loop. No sense in advancing further
            return Option.none();
        }
        
        return edge.follow(endNode).map(n -> new PcValue<>(startNode, n, edges.append(edge)));
    }
}
