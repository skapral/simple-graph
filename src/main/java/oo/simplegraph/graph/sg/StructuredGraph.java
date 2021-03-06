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

import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.edge.meta.EdgeMeta;
import oo.simplegraph.node.Node;
import oo.simplegraph.node.meta.NodeMeta;

/**
 * A graph representation, which presents the graph's structure
 * 
 * @author Kapralov Sergey
 */
public interface StructuredGraph<T, M> {
    /**
     * StructuredGraph's inference
     * 
     * @param <T>
     * @param <M> 
     */
    interface Inference<T, M> {
        StructuredGraph<T, M> graph();
    }
    
    Set<Node<T>> nodes();
    Set<Edge<T>> edges();
    NodeMeta<T, M> nodeMeta();
    EdgeMeta<T, M> edgeMeta();
}
