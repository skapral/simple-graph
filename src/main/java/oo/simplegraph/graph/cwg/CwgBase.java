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
package oo.simplegraph.graph.cwg;

import java.util.Objects;
import java.util.function.BiFunction;
import javaslang.collection.HashMap;
import javaslang.collection.Map;
import oo.simplegraph.edge.EWeighted;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.cdg.CdgSimpleGraph;
import oo.simplegraph.graph.cdg.ConstructedDirectGraph;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.NWeighted;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class CwgBase<T> implements ConstructedWeightGraph<T> {

    private final ConstructedDirectGraph<T> delegate;
    private final Map<Node<T>, Integer> nodeWeights; //TODO: think how to avoid it
    private final BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory; //TODO: think how to avoid it

    public CwgBase(BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this(
                new CdgSimpleGraph(),
                HashMap.empty(),
                edgeFactory
        );
    }

    public CwgBase(ConstructedDirectGraph<T> delegate, Map<Node<T>, Integer> nodeWeights, BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this.delegate = delegate;
        this.nodeWeights = nodeWeights;
        this.edgeFactory = edgeFactory;
    }

    @Override
    public final ConstructedWeightGraph<T> withNode(Node<T> node, int weight) {
        Map<Node<T>, Integer> newNodeWeights = nodeWeights.put(node, weight);
        ConstructedDirectGraph<T> newDelegate = delegate.withNode(
                new NWeighted<>(
                        node,
                        weight
                )
        );

        return new CwgBase<>(
                newDelegate,
                newNodeWeights,
                edgeFactory
        );
    }

    @Override
    public final ConstructedWeightGraph<T> withEdge(Node<T> node1, Node<T> node2, int weight) {
        ConstructedDirectGraph<T> newDelegate = delegate.withEdge(
                new EWeighted<>(
                        edgeFactory.apply(
                                new NWeighted<>(
                                        node1,
                                        nodeWeights.get(node1).getOrElseThrow(RuntimeException::new)
                                ),
                                new NWeighted<>(
                                        node2,
                                        nodeWeights.get(node2).getOrElseThrow(RuntimeException::new)
                                )
                        ),
                        weight
                )
        );

        return new CwgBase<>(
                newDelegate,
                nodeWeights,
                edgeFactory
        );
    }

    @Override
    public final StructuredGraph<T> result() {
        return delegate.result();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(delegate, nodeWeights, edgeFactory);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof CwgBase) {
            final CwgBase other = (CwgBase) obj;
            return Objects.equals(this.delegate, other.delegate)
                    && Objects.equals(this.nodeWeights, other.nodeWeights)
                    && Objects.equals(this.edgeFactory, other.edgeFactory);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "CwgBase{" + "delegate=" + delegate + ", nodeWeights=" + nodeWeights + ", edgeFactory=" + edgeFactory + '}';
    }
}
