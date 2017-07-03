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
package oo.simplegraph.graph.cpgwm;

import java.util.Objects;
import java.util.function.BiFunction;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.cgwm.CgwmSimple;
import oo.simplegraph.graph.cgwm.ConstructedGraphWithMeta;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.Node;

/**
 * Basic implementation for {@link ConstructedPrettyGraphWithMeta}
 * 
 * @author Kapralov Sergey
 */
public class CpgwmBase<T, M> implements ConstructedPrettyGraphWithMeta<T, M> {
    private final ConstructedGraphWithMeta<T, M> cg;
    private final BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory;

    public CpgwmBase(BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this(
                new CgwmSimple<>(), 
                edgeFactory
        );
    }
    
    public CpgwmBase(ConstructedGraphWithMeta<T, M> cg, BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this.cg = cg;
        this.edgeFactory = edgeFactory;
    }
    
    @Override
    public final ConstructedPrettyGraphWithMeta<T, M> withNode(Node<T> node, M meta) {
        return new CpgwmBase(
                cg.withNode(node, meta),
                edgeFactory
        );
    }

    @Override
    public final ConstructedPrettyGraphWithMeta<T, M> withEdge(Node<T> node1, Node<T> node2, M meta) {
        return new CpgwmBase<>(
                cg.withEdge(
                        edgeFactory.apply(node1, node2),
                        meta
                ),
                edgeFactory
        );
    }

    @Override
    public final StructuredGraph<T, M> result() {
        return cg.result();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(cg, edgeFactory);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof CpgwmBase) {
            final CpgwmBase<?, ?> other = (CpgwmBase<?, ?>) obj;
            return Objects.equals(this.cg, other.cg) &&
                   Objects.equals(this.edgeFactory, other.edgeFactory);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "CpgwmBase{" + "cg=" + cg + ", edgeFactory=" + edgeFactory + '}';
    }
}
