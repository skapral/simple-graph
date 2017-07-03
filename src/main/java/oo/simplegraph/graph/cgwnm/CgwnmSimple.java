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
package oo.simplegraph.graph.cgwnm;

import java.util.Objects;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.cgwm.CgwmSimple;
import oo.simplegraph.graph.cgwm.ConstructedGraphWithMeta;
import oo.simplegraph.graph.sg.StructuredGraph;
import oo.simplegraph.node.Node;

/**
 * Basic implementation of {@link ConstructedGraphWithNoMeta}
 * 
 * @author Kapralov Sergey
 */
public class CgwnmSimple<T> implements ConstructedGraphWithNoMeta<T> {

    private final ConstructedGraphWithMeta<T, Void> cgwm;

    public CgwnmSimple() {
        this(
                new CgwmSimple<T, Void>()
        );
    }

    public CgwnmSimple(ConstructedGraphWithMeta<T, Void> cgwm) {
        this.cgwm = cgwm;
    }

    @Override
    public final ConstructedGraphWithNoMeta<T> withNode(Node<T> node) {
        return new CgwnmSimple(
                cgwm.withNode(node, null)
        );
    }

    @Override
    public final ConstructedGraphWithNoMeta<T> withEdge(Edge<T> edge) {
        return new CgwnmSimple(
                cgwm.withEdge(edge, null)
        );
    }

    @Override
    public final StructuredGraph<T, Void> result() {
        return cgwm.result();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.cgwm);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof CgwnmSimple) {
            final CgwnmSimple other = (CgwnmSimple) obj;
            return Objects.equals(this.cgwm, other.cgwm);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "CgwnmSimple{" + "cgwm=" + cgwm + '}';
    }
}
