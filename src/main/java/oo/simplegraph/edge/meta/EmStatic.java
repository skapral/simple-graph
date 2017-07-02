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
package oo.simplegraph.edge.meta;

import java.util.Objects;
import javaslang.collection.Map;
import oo.simplegraph.edge.Edge;

/**
 *
 * @author Kapralov Sergey
 */
public class EmStatic<T, M> implements EdgeMeta<T, M> {
    private final Map<Edge<T>, M> edgesMeta;

    public EmStatic(Map<Edge<T>, M> edgesMeta) {
        this.edgesMeta = edgesMeta;
    }
    
    @Override
    public final M metaForEdge(Edge<T> edge) {
        return edgesMeta.get(edge).getOrElseThrow(NullPointerException::new);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(edgesMeta);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof EmStatic) {
            final EmStatic other = (EmStatic) obj;
            return Objects.equals(this.edgesMeta, other.edgesMeta);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "EmStatic{" + "edgesMeta=" + edgesMeta + '}';
    }
}
