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
import javaslang.collection.Set;
import javaslang.control.Option;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class EWeighted<T> implements EdgeWeighted<T> {
    private final Edge<T> edge;
    private final int weight;

    public EWeighted(Edge<T> edge, int weight) {
        this.edge = edge;
        this.weight = weight;
    }
    
    @Override
    public final int weight() {
        return weight;
    }

    @Override
    public final Set<Node<T>> nodes() {
        return edge.nodes();
    }

    @Override
    public final Set<Node<T>> startingNodes() {
        return edge.startingNodes();
    }

    @Override
    public final Option<Node<T>> follow(Node<T> node) {
        return edge.follow(node);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(edge, weight);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof EWeighted) {
            final EWeighted other = (EWeighted) obj;
            return Objects.equals(this.edge, other.edge) &&
                   Objects.equals(this.weight, other.weight);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "EWeighed{" + "edge=" + edge + ", weight=" + weight + '}';
    }
}
