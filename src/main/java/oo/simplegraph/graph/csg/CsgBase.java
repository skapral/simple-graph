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
package oo.simplegraph.graph.csg;

import oo.simplegraph.graph.cdg.CdgSimpleGraph;
import oo.simplegraph.graph.cdg.ConstructedDirectGraph;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class CsgBase<T> implements ConstructedSimpleGraph<T> {
    private final ConstructedDirectGraph<T> delegate;
    private final Function<? super Node<T>, ? extends Node<T>> nodeFactory;
    private final BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory;

    public CsgBase(BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this(new CdgSimpleGraph<>(),
                n -> n,
                edgeFactory
        );
    }

    public CsgBase(ConstructedDirectGraph<T> delegate, Function<? super Node<T>, ? extends Node<T>> nodeFactory, BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this.delegate = delegate;
        this.nodeFactory = nodeFactory;
        this.edgeFactory = edgeFactory;
    }
    
    @Override
    public final ConstructedSimpleGraph<T> withNode(Node<T> node) {
        return new CsgBase<>(
                delegate.withNode(node),
                nodeFactory,
                edgeFactory
        );
    }

    @Override
    public final ConstructedSimpleGraph<T> withEdge(Node<T> node1, Node<T> node2) {
        return new CsgBase<>(
                delegate.withEdge(edgeFactory.apply(
                        nodeFactory.apply(node1), 
                        nodeFactory.apply(node2)
                )),
                nodeFactory,
                edgeFactory
        );
    }

    @Override
    public final StructuredGraph<T> result() {
        return delegate.result();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.delegate);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof CsgBase) {
            final CsgBase other = (CsgBase) obj;
            return Objects.equals(this.delegate, other.delegate);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "CsgDirected{" + "nodes=" + delegate + '}';
    }
}
