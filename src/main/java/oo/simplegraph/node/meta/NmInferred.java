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
public class NmInferred<N, M> implements NodeMeta<N, M> {
    private final Inference<N, M> inference;

    public NmInferred(Inference<N, M> inference) {
        this.inference = inference;
    }
    
    @Override
    public final M metaForNode(Node<N> node) {
        return inference.nodeMeta().metaForNode(node);
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.inference);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NmInferred) {
            final NmInferred other = (NmInferred) obj;
            return Objects.equals(this.inference, other.inference);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NmInferred{" + "inference=" + inference + '}';
    }
}
