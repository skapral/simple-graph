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
package oo.simplegraph.graph.ng;

import java.util.Objects;
import javaslang.collection.List;
import oo.simplegraph.api.Edge;
import oo.simplegraph.api.Node;
import oo.simplegraph.api.NavigableGraph;

/**
 *
 * @author Kapralov Sergey
 */
public class NgInferred<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements NavigableGraph<T, ND, ED> {
    private final NavigableGraph.Inference<T, ND, ED> graphInference;

    public NgInferred(Inference<T, ND, ED> graphInference) {
        this.graphInference = graphInference;
    }

    @Override
    public final List<ED> edges(ND node) {
        return graphInference.graph().edges(node);
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.graphInference);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NgInferred<T, ND, ED> other = (NgInferred<T, ND, ED>) obj;
        if (!Objects.equals(this.graphInference, other.graphInference)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "NgInferred{" + "graphInference=" + graphInference + '}';
    }
}