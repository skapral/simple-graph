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
package oo.simplegraph.graph.tg;

import java.util.Objects;
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.ng.NavigableGraph;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class TgFromNavigableGraph<T> implements TraversableGraph<T> {
    private final NavigableGraph<T> graph;
    private final TraversableGraph<T> previousIteration;
    private final Set<Node<T>> traverseNodes;
    private final Set<Edge<T>> traversedEdges;

    public TgFromNavigableGraph(NavigableGraph<T> graph, TraversableGraph<T> previousIteration, Set<Node<T>> traverseNodes, Set<Edge<T>> traversedEdges) {
        this.graph = graph;
        this.previousIteration = previousIteration;
        this.traverseNodes = traverseNodes;
        this.traversedEdges = traversedEdges;
    }
    
    public TgFromNavigableGraph(NavigableGraph<T> graph, Set<Node<T>> startingNodes) {
        this(
                graph,
                new TgNull<>(),
                startingNodes,
                HashSet.empty()
        );
    }
    
    
    public TgFromNavigableGraph(NavigableGraph<T> graph, Node<T>... startingNodes) {
        this(
                graph,
                HashSet.of(startingNodes)
        );
    }
    
    @Override
    public final Set<Node<T>> traverseNodes() {
        return traverseNodes;
    }

    @Override
    public final Set<Edge<T>> traversedEdges() {
        return traversedEdges;
    }
    
    @Override
    public final TraversableGraph<T> previousIteration() {
        return previousIteration;
    }

    @Override
    public final TraversableGraph<T> nextIteration() {
        final Set<Edge<T>> newPassedEdges = traverseNodes.flatMap(n -> graph.edges(n)).removeAll(traversedEdges);
        final Set<Node<T>> newStartingNodes = newPassedEdges.flatMap(
                e -> traverseNodes.flatMap(n -> e.follow(n))
        );
        
        return new TgFromNavigableGraph<>(
                graph,
                this,
                newStartingNodes,
                newPassedEdges.addAll(traversedEdges)
        );
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
                graph, 
                previousIteration, 
                traverseNodes, 
                traversedEdges
        );
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof TgFromNavigableGraph) {
            final TgFromNavigableGraph other = (TgFromNavigableGraph) obj;
            return Objects.equals(this.graph, other.graph) &&
                   Objects.equals(this.previousIteration, other.previousIteration) &&
                   Objects.equals(this.traverseNodes, other.traverseNodes) &&
                   Objects.equals(this.traversedEdges, other.traversedEdges);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "TgFromNavigableGraph{" + "graph=" + graph + ", previousIteration=" + previousIteration + ", traverseNodes=" + traverseNodes + ", traversedEdges=" + traversedEdges + '}';
    }
}
