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


import java.util.Objects;
import javaslang.collection.HashSet;
import javaslang.collection.List;
import javaslang.collection.Set;
import javaslang.control.Option;
import oo.simplegraph.node.Node;

/**
 *
 * @author Kapralov Sergey
 */
public class EBiDirected<T, ND extends Node<T>, ED extends Edge<T, ND, ED>> implements Edge<T, ND, ED> {
    private final ND node1;
    private final ND node2;

    public EBiDirected(ND node1, ND node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
    
    @Override
    public final Option<ND> follow(ND node) {
        if(node.equals(node1)) {
            return Option.of(node2);
        } else if(node.equals(node2)) {
            return Option.of(node1);
        } else {
            return Option.none();
        }
    }

    @Override
    public final Set<ND> nodes() {
        return HashSet.of(node1, node2);
    }

    @Override
    public final Set<ND> startingNodes() {
        return nodes();
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.node1);
        hash = 41 * hash + Objects.hashCode(this.node2);
        return hash;
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
        final EBiDirected<T, ND, ED> other = (EBiDirected<T, ND, ED>) obj;
        
        Set<ND> thisNodes = this.nodes();
        Set<ND> otherNodes = other.nodes();
        if(!thisNodes.equals(otherNodes)) {
            return false;
        }
        
        return true;
    }

    @Override
    public final String toString() {
        return "EBiDirected{" + HashSet.of(this.node1, this.node2).map(Node::value) + '}';
    }
}
