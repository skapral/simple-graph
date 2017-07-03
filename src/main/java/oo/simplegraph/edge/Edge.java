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
package oo.simplegraph.edge;

import javaslang.collection.Set;
import javaslang.control.Option;
import oo.simplegraph.node.Node;

/**
 * Graph's edge
 * 
 * @author Kapralov Sergey
 * @param <V> type of nodes this edge references to
 */
public interface Edge<V> {
    /**
     * The nodes, this edge is references
     * 
     * @return the two nodes
     */
    Set<Node<V>> nodes();
    
    /**
     * The starting nodes of the edge. For each node returned, 
     * {@link Edge::follow} returns non-empty option
     * 
     * @return 
     */
    Set<Node<V>> startingNodes();
    
    /**
     * Try to follow the edge's direction, starting from node passed.
     * 
     * @param node
     * @return optional adjacent node, or empty in case of wrong direction
     */
    Option<Node<V>> follow(Node<V> node);
}
