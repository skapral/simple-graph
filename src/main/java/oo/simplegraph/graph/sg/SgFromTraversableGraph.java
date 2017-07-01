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
import oo.simplegraph.graph.tg.TraversableGraph;
import oo.simplegraph.node.Node;


class SgFromTraversableGraphInference<N extends Node<?>, E extends Edge<N, ?>> implements StructuredGraph.Inference<N, E> {
    private final TraversableGraph<N, E> tg;

    public SgFromTraversableGraphInference(TraversableGraph<N, E> tg) {
        this.tg = tg;
    }
    
    @Override
    public final StructuredGraph<N, E> graph() {
        TraversableGraph<N, E> tgn;
        for(
                tgn = tg;
                !tgn.traverseNodes().isEmpty();
                tgn = tgn.nextIteration()
        ) {}
        Set<E> edges = tgn.traversedEdges();
        
        return new StructuredGraph<N, E>() {
            @Override
            public Set<N> nodes() {
                return edges.flatMap(Edge::nodes);
            }

            @Override
            public Set<E> edges() {
                return edges;
            }
        };
    }

    @Override
    public final int hashCode() {
        return Objects.hash(tg);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SgFromTraversableGraphInference) {
            final SgFromTraversableGraphInference other = (SgFromTraversableGraphInference) obj;
            return Objects.equals(this.tg, other.tg);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "SgFromTraversableGraphInference{" + "tg=" + tg + '}';
    }
}


/**
 *
 * @author Kapralov Sergey
 */
public class SgFromTraversableGraph<N extends Node<?>, E extends Edge<N, ?>> extends SgInferred<N, E> implements StructuredGraph<N, E> {
    public SgFromTraversableGraph(TraversableGraph<N, E> tg) {
        super(
                new SgFromTraversableGraphInference<>(tg)
        );
    }
}
