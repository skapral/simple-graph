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
package oo.simplegraph.graph.sg;

import java.util.Objects;
import java.util.function.Function;
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class SgEmpty<N extends Node<?>, E extends Edge<N, E>>  implements StructuredGraph<N, E> {
    private final Function<? super N, ?> nodesIdentitySpec;
    private final Function<? super E, ?> edgesIdentitySpec;

    public SgEmpty() {
        this(
                n->n,
                e->e
        );
    }
    
    public SgEmpty(Function<? super N, ?> nodesIdentitySpec, Function<? super E, ?> edgesIdentitySpec) {
        this.nodesIdentitySpec = nodesIdentitySpec;
        this.edgesIdentitySpec = edgesIdentitySpec;
    }
    
    @Override
    public final Set<N> nodes() {
        return HashSet.<N>empty().distinctBy(nodesIdentitySpec);
    }

    @Override
    public final Set<E> edges() {
        return HashSet.<E>empty().distinctBy(edgesIdentitySpec);
    }

    @Override
    public final boolean equals(Object obj) {
        if(obj instanceof SgEmpty) {
            SgEmpty other = (SgEmpty) obj;
            return Objects.equals(this.nodesIdentitySpec, other.nodesIdentitySpec) &&
                   Objects.equals(this.edgesIdentitySpec, other.edgesIdentitySpec);
        } else {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return Objects.hash();
    }

    @Override
    public final String toString() {
        return "SgEmpty{}";
    }
}
