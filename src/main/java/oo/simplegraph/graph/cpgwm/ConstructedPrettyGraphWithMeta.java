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
package oo.simplegraph.graph.cpgwm;

import oo.simplegraph.edge.Edge;
import oo.simplegraph.graph.cg.ConstructedGraph;
import oo.simplegraph.node.Node;

/**
 * Constructs a graph from passed nodes
 * 
 * @author Kapralov Sergey
 */
public interface ConstructedPrettyGraphWithMeta<T, M> extends ConstructedGraph<T, M> {
    /**
     * Adds new node to the graph
     * 
     * @param node
     * @param meta
     * @return 
     */
    ConstructedPrettyGraphWithMeta<T, M> withNode(Node<T> node, M meta);
    
    /**
     * Adds new edge to the graph
     * 
     * @param node1
     * @param node2
     * @param meta
     * @return 
     */
    ConstructedPrettyGraphWithMeta<T, M> withEdge(Node<T> node1, Node<T> node2, M meta);
}
