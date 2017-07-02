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
package oo.simplegraph.graph.cpgwnm;

import oo.simplegraph.graph.cpgwm.*;
import java.util.function.BiFunction;
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.cgwm.CgwmSimple;
import oo.simplegraph.graph.cgwm.ConstructedGraphWithMeta;
import oo.simplegraph.graph.cgwnm.CgwnmSimple;
import oo.simplegraph.graph.cgwnm.ConstructedGraphWithNoMeta;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class CpgwnmBase<T> implements ConstructedPrettyGraphWithNoMeta<T> {
    private final ConstructedGraphWithNoMeta<T> cg;
    private final BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory;

    public CpgwnmBase(BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this(
                new CgwnmSimple<>(), 
                edgeFactory
        );
    }
    
    public CpgwnmBase(ConstructedGraphWithNoMeta<T> cg, BiFunction<? super Node<T>, ? super Node<T>, ? extends Edge<T>> edgeFactory) {
        this.cg = cg;
        this.edgeFactory = edgeFactory;
    }
    
    @Override
    public ConstructedPrettyGraphWithNoMeta<T> withNode(Node<T> node) {
        return new CpgwnmBase(
                cg.withNode(node),
                edgeFactory
        );
    }

    @Override
    public ConstructedPrettyGraphWithNoMeta<T> withEdge(Node<T> node1, Node<T> node2) {
        return new CpgwnmBase<>(
                cg.withEdge(
                        edgeFactory.apply(node1, node2)
                ),
                edgeFactory
        );
    }

    @Override
    public StructuredGraph<T, Void> result() {
        return cg.result();
    }
}
