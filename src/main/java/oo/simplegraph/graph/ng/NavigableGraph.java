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

import javaslang.collection.Array;
import oo.simplegraph.edge.Edge;
import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.edge.meta.EdgeMeta;
import oo.simplegraph.node.Node;
import oo.simplegraph.node.meta.NodeMeta;

/**
 *
 * @author Kapralov Sergey
 */
public interface NavigableGraph<T, M> {
    interface Inference<T, M> {
        NavigableGraph<T, M> graph();
    }
    
    List<Edge<T>> adjacentEdges(Node<T> node);
    default Option<Edge<T>> findEdge(Node<T> node, Node<T> node2) {
        return Option.sequence(
                Array.of(
                    adjacentEdges(node).filter(e -> e.follow(node).equals(node2)).headOption(),
                    adjacentEdges(node2).filter(e -> e.follow(node).equals(node)).headOption()
                )
        ).flatMap(list -> list.headOption());
    }
    
    NodeMeta<T, M> nodeMeta();
    EdgeMeta<T, M> edgeMeta();
}
