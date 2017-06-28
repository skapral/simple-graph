/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oo.simplegraph.pft.dijkstra;

import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.api.Edge;
import oo.simplegraph.api.Graph;
import oo.simplegraph.api.Node;
import oo.simplegraph.api.PathFindingTask;

/**
 *
 * @author Kapralov Sergey
 */
public class PftDijkstra<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements PathFindingTask<T, ND, ED> {
    private final Graph<T, ND, ED> graph;

    public PftDijkstra(Graph<T, ND, ED> graph) {
        this.graph = graph;
    }

    @Override
    public final Option<List<ED>> path(ND nodeStart, ND nodeEnd) {
        if (nodeStart.equals(nodeEnd)) {
            return Option.of(List.empty());
        }
        throw new UnsupportedOperationException();
    }
}
