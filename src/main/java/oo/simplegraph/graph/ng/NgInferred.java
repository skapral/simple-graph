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
package oo.simplegraph.graph.ng;

import java.util.Objects;
import javaslang.collection.List;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.edge.meta.EdgeMeta;
import oo.simplegraph.node.Node;
import oo.simplegraph.node.meta.NodeMeta;

/**
 * Inferred navigable graph
 * 
 * @author Kapralov Sergey
 */
public class NgInferred<T, M> implements NavigableGraph<T, M> {
    private final NavigableGraph.Inference<T, M> graphInference;

    public NgInferred(Inference<T, M> graphInference) {
        this.graphInference = graphInference;
    }

    @Override
    public final List<Edge<T>> adjacentEdges(Node<T> node) {
        return graphInference.graph().adjacentEdges(node);
    }

    @Override
    public final NodeMeta<T, M> nodeMeta() {
        return graphInference.graph().nodeMeta();
    }

    @Override
    public final EdgeMeta<T, M> edgeMeta() {
        return graphInference.graph().edgeMeta();
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(this.graphInference);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof NgInferred) {
            final NgInferred other = (NgInferred) obj;
            return Objects.equals(this.graphInference, other.graphInference);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "NgInferred{" + "graphInference=" + graphInference + '}';
    }
}
