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

import javaslang.Tuple2;
import javaslang.Tuple3;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.ng.NavigableGraph;
import oo.simplegraph.graph.tt.TraversalTransformation;
import oo.simplegraph.node.Node;



class SgTraversedInference<N extends Node<?>, E extends Edge<N, E>, NX extends Node<?>, EX extends Edge<NX, EX>> implements StructuredGraph.Inference<N, E> {
    private final NavigableGraph<NX, EX> graph;
    private final NX node;
    private final TraversalTransformation<N, E, NX, EX> tt;

    public SgTraversedInference(NavigableGraph<NX, EX> graph, NX node, TraversalTransformation<N, E, NX, EX> tt) {
        this.graph = graph;
        this.node = node;
        this.tt = tt;
    }

    @Override
    public final StructuredGraph<N, E> graph() {
        // TODO: This method needs to be refactored somehow
        List<NX> nodes = List.of(node);
        Set<E> allEdges = HashSet.empty();
        Set<EX> visitedEdges = HashSet.empty();
        while(!nodes.isEmpty()) {
            final Set<EX> _visitedEdges = visitedEdges;
            List<Tuple2<NX, EX>> possiblePaths = nodes
                    .flatMap(n -> 
                            graph.edges(n)
                                    .removeAll(_visitedEdges)
                                    .map(e -> new Tuple2<>(n, e))
                    );
            
            List<Tuple3<NX, NX, EX>> pathsFollowed = possiblePaths
                    .<Tuple3<NX, NX, EX>>flatMap(tpl -> {
                        NX startNode = tpl._1;
                        EX edge = tpl._2;
                        return edge.follow(startNode).toList()
                                .map(endNode -> new Tuple3(startNode, endNode, edge));
                    });
            
            List<Tuple3<N, N, E>> pathsTransformed = pathsFollowed
                    .map(tpl -> new Tuple3(
                            tpl._1,
                            tpl._2,
                            tt.overridePath(tpl._1, tpl._2, tpl._3)
                    ));
            
            allEdges = allEdges.addAll(pathsTransformed.map(Tuple3::_3));
            nodes = pathsFollowed.map(Tuple3::_2);
            visitedEdges = visitedEdges.addAll(pathsFollowed.map(Tuple3::_3));
        }
        
        final Set<E> _allEdges = allEdges;
        return new StructuredGraph<N, E>() {
            @Override
            public Set<N> nodes() {
                return _allEdges.flatMap(e -> e.nodes());
            }

            @Override
            public Set<E> edges() {
                return _allEdges;
            }
        };
    }
}


/**
 *
 * @author Kapralov Sergey
 */
public class SgTraversed<N extends Node<?>, E extends Edge<N, E>, NX extends Node<?>, EX extends Edge<NX, EX>> extends SgInferred<N, E> implements StructuredGraph<N, E> {
    public SgTraversed(NavigableGraph<NX, EX> graph, NX node, TraversalTransformation<N, E, NX, EX> tt) {
        super(
            new SgTraversedInference(graph, node, tt)
        );
    }
}
