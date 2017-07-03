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
package oo.simplegraph.graph.cg.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.ng.NavigableGraph;
import oo.simplegraph.graph.sg.SgSimple;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.Node;

/**
 * Constructed Dijkstra tree graph implementation
 * 
 * @author Kapralov Sergey
 */
public class DgImplementation<T> implements DijkstraGraph<T> {
    private final NavigableGraph<T, Integer> graph;
    private final Node<T> source;

    public DgImplementation(NavigableGraph<T, Integer> graph, Node<T> source) {
        this.graph = graph;
        this.source = source;
    }

    @Override
    public final StructuredGraph<T, Integer> result() {
        Queue<Node<T>> traverseQueue = new LinkedList<>();
        Map<Node<T>, Integer> nodeScores = new HashMap<>();
        Set<Node<T>> closedNodes = HashSet.empty();
        Map<Node<T>, Edge<T>> edges = new HashMap<>();
        
        nodeScores.put(source, graph.nodeMeta().metaForNode(source));
        traverseQueue.add(source);
        while(!traverseQueue.isEmpty()) {
            Node<T> node = traverseQueue.remove();
            final Set<Node<T>> _closedNodes = closedNodes;
            final Node<T> _node = node;
            List<Edge<T>> adjEdges = graph.adjacentEdges(node)
                    .filter(e -> !_closedNodes.contains(e.follow(_node).get()))
                    .sortBy(e -> graph.edgeMeta().metaForEdge(e));
            List<Node<T>> adjNodes = adjEdges.flatMap(e -> e.follow(node));
            traverseQueue.addAll(adjNodes.toJavaList());
            
            for(Edge<T> adjEdge : adjEdges) {
                Integer adjEdgeScore = graph.edgeMeta().metaForEdge(adjEdge);
                Node<T> adjNode = adjEdge.follow(node).get();
                Integer adjNodeScore = graph.nodeMeta().metaForNode(adjNode);
                
                Integer initScore = nodeScores.getOrDefault(adjNode, Integer.MAX_VALUE);
                Integer resultScore = nodeScores.merge(adjNode, adjNodeScore + adjEdgeScore, (i1, i2) -> i1 < i2 ? i1 : i2);
                
                if(resultScore < initScore) {
                    edges.put(adjNode, adjEdge);
                }
                
            }
            
            closedNodes = closedNodes.add(node);
            
        }
        
        return new SgSimple<>(
                HashSet.ofAll(edges.values()).flatMap(Edge::nodes),
                HashSet.ofAll(edges.values()),
                graph.nodeMeta(),
                graph.edgeMeta()
        );
    }

    @Override
    public final int hashCode() {
        return Objects.hash(graph, source);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof DgImplementation) {
            final DgImplementation<?> other = (DgImplementation<?>) obj;
            return Objects.equals(this.graph, other.graph) &&
                   Objects.equals(this.source, other.source);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "DgImplementation{" + "graph=" + graph + ", source=" + source + '}';
    }
}
