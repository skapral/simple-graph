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
package oo.simplegraph.node.meta;

import java.util.Objects;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class NwmBase<T, M> implements NodeWithMeta<T, M>{
    private final Node<T> node;
    private final M meta;

    public NwmBase(Node<T> node, M meta) {
        this.node = node;
        this.meta = meta;
    }

    @Override
    public final Node<T> node() {
        return node;
    }

    @Override
    public final M meta() {
        return meta;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(node, meta);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NwmBase) {
            final NwmBase<?, ?> other = (NwmBase<?, ?>) obj;
            return Objects.equals(this.node, other.node) &&
                   Objects.equals(this.meta, other.meta);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NwmBase{" + "node=" + node + ", meta=" + meta + '}';
    }
}
