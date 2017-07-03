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
package oo.simplegraph.edge;

import java.util.Objects;
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import javaslang.control.Option;
import oo.simplegraph.node.Node;

/**
 * One-directed graph edge
 * 
 * @author Kapralov Sergey
 * @param <V> type of nodes this edge references to
 */
public class EDirected<V> implements Edge<V> {
    private final Node<V> start;
    private final Node<V> end;

    public EDirected(Node<V> start, Node<V> end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public final Option<Node<V>> follow(Node<V> node) {
        return node.equals(start) ? Option.of(end) : Option.none();
    }
    
    @Override
    public final Set<Node<V>> nodes() {
        return HashSet.of(start, end);
    }
    
    @Override
    public final Set<Node<V>> startingNodes() {
        return HashSet.of(start);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
                this.start,
                this.end
        );
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof EDirected) {
            final EDirected other = (EDirected) obj;
            return Objects.equals(this.start, other.start) &&
                    Objects.equals(this.end, other.end);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "EDirected{" + "start=" + start + ", end=" + end + '}';
    }
}
