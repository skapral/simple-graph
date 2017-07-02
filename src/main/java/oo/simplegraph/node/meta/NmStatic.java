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

import oo.simplegraph.edge.meta.*;
import java.util.Objects;
import javaslang.collection.Map;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class NmStatic<T, M> implements NodeMeta<T, M> {
    private final Map<Node<T>, M> nodesMeta;

    public NmStatic(Map<Node<T>, M> edgesMeta) {
        this.nodesMeta = edgesMeta;
    }
    
    @Override
    public final M metaForNode(Node<T> edge) {
        return nodesMeta.get(edge).getOrElseThrow(NullPointerException::new);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(nodesMeta);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NmStatic) {
            final NmStatic other = (NmStatic) obj;
            return Objects.equals(this.nodesMeta, other.nodesMeta);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NmStatic{" + "nodesMeta=" + nodesMeta + '}';
    }
}
