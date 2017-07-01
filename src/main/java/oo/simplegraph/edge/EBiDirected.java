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
 *
 * @author Kapralov Sergey
 */
public class EBiDirected<ND extends Node<?>, ED extends Edge<ND, ED>> implements Edge<ND, ED> {

    private final ND node1;
    private final ND node2;

    public EBiDirected(ND node1, ND node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public final Option<ND> follow(ND node) {
        if (node.equals(node1)) {
            return Option.of(node2);
        } else if (node.equals(node2)) {
            return Option.of(node1);
        } else {
            return Option.none();
        }
    }

    @Override
    public final Set<ND> nodes() {
        return HashSet.of(node1, node2);
    }

    @Override
    public final Set<ND> startingNodes() {
        return nodes();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(
                this.node1,
                this.node2
        );
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof EBiDirected) {
            final EBiDirected<ND, ED> other = (EBiDirected<ND, ED>) obj;
            return Objects.equals(this.nodes(), other.nodes());
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "EBiDirected{" + HashSet.of(this.node1, this.node2) + '}';
    }
}
