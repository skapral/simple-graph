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

import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class TgNull<T> implements TraversableGraph<T> {
    @Override
    public final Set<Node<T>> traverseNodes() {
        throw new NullPointerException("attempt to access Null traversable graph"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public final Set<Edge<T>> traversedEdges() {
        throw new NullPointerException("attempt to access Null traversable graph"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public final TraversableGraph<T> previousIteration() {
        throw new NullPointerException("attempt to access Null traversable graph"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public final TraversableGraph<T> nextIteration() {
        throw new NullPointerException("attempt to access Null traversable graph"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public final boolean equals(Object obj) {
        return obj instanceof TgNull;
    }

    @Override
    public final int hashCode() {
        return 42;
    }

    @Override
    public String toString() {
        return "TgNull{}";
    }
}
