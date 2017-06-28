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
import javaslang.collection.HashSet;
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;


class SgWithNodesInference<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements StructuredGraph.Inference<T, ND, ED> {
    private final StructuredGraph<T, ND, ED> sg;
    private final Set<ND> nodes;

    public SgWithNodesInference(StructuredGraph<T, ND, ED> sg, Set<ND> nodes) {
        this.sg = sg;
        this.nodes = nodes;
    }
    
    @Override
    public final StructuredGraph<T, ND, ED> graph() {
        return new SgSimple<>(sg.nodes().addAll(nodes), sg.edges());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.sg, this.nodes);
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
        final SgWithNodesInference<?, ?, ?> other = (SgWithNodesInference<?, ?, ?>) obj;
        if (!Objects.equals(this.sg, other.sg)) {
            return false;
        }
        if (!Objects.equals(this.nodes, other.nodes)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "SgWithNodesInference{" + "sg=" + sg + ", nodes=" + nodes + '}';
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class SgWithNodes<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> extends SgInferred<T, ND, ED> implements StructuredGraph<T, ND, ED> {
    public SgWithNodes(StructuredGraph<T, ND, ED> sg, Set<ND> nodes) {
        super(
            new SgWithNodesInference<>(
                sg, nodes
            )
        );
    }
    
    public SgWithNodes(StructuredGraph<T, ND, ED> sg, ND... nodes) {
        this(sg, HashSet.of(nodes));
    }
}
