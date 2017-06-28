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
package oo.simplegraph.main;

import oo.simplegraph.api.Edge;
import oo.simplegraph.api.Graph;
import oo.simplegraph.api.Node;
import oo.simplegraph.edge.EDirected;
import oo.simplegraph.graph.GFromEdges;
import oo.simplegraph.node.NValue;
import oo.simplegraph.path.naive.PftNaive;

class NString extends NValue<String> {
    public NString(String value) {
        super(value);
    }
}

/**
 *
 * @author Kapralov Sergey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Node a = new NString("a");
        final Node a1 = new NString("a1");
        final Node a2 = new NString("a2");
        final Node a3 = new NString("a3");
        final Node b1 = new NString("b1");
        final Node b2 = new NString("b2");
        final Node b3 = new NString("b3");
        final Node b = new NString("b");
        
        final Edge aa1 = new  EDirected(a, a1);
        final Edge aa2 = new  EDirected(a, a2);
        final Edge aa3 = new  EDirected(a, a3);
        
        final Edge a1b1 = new EDirected(a1, b1);
        final Edge a1b2 = new EDirected(a1, b2);
        final Edge a2b1 = new EDirected(a2, b1);
        final Edge a2b3 = new EDirected(a2, b3);
        final Edge a3b2 = new EDirected(a3, b2);
        final Edge a3b3 = new EDirected(a3, b3);
        
        final Edge b1b = new  EDirected(b1, b);
        final Edge b2b = new  EDirected(b2, b);
        final Edge b3b = new  EDirected(b3, b);
        
        Graph g = new GFromEdges(
            aa1, 
            aa2,
            aa3,
            a1b1,
            a1b2,
            a2b1,
            a2b3,
            a3b2,
            a3b3,
            b1b,
            b2b,
            b3b
        );
        System.out.println(g);
        System.out.println(new PftNaive<>(g).path(a, a));
    }
    
}
