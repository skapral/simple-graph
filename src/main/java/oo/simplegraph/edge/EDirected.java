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
public class EDirected<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements Edge<T, ND, ED> {
    private final ND start;
    private final ND end;

    public EDirected(ND start, ND end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public final Option<ND> follow(ND node) {
        return node.equals(start) ? Option.of(end) : Option.none();
    }
    
    @Override
    public final Set<ND> nodes() {
        return HashSet.of(start, end);
    }
    
    @Override
    public final Set<ND> startingNodes() {
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
            final EDirected<T, ND, ED> other = (EDirected<T, ND, ED>) obj;
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
