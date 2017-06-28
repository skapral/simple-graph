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

import oo.simplegraph.edge.EDirected;
import oo.simplegraph.node.NValue;
import oo.simplegraph.pft.naive.PftNaive;
import oo.simplegraph.api.NavigableGraph;
import oo.simplegraph.api.StructuredGraph;
import oo.simplegraph.graph.ng.NgFromStructure;
import oo.simplegraph.graph.sg.SgEmpty;
import oo.simplegraph.graph.sg.SgWithEdges;
import oo.simplegraph.graph.sg.SgWithNodes;


/**
 * User-defined node
 * 
 * @author Kapralov Sergey
 */
class NString extends NValue<String> {
    public NString(String value) {
        super(value);
    }
}

/**
 * User-defined edge
 * 
 * @author Kapralov Sergey
 */
class EString extends EDirected<String, NString, EString> {
    public EString(String start, String end) {
        super(
                new NString(start),
                new NString(end)
        );
    }
    
    public EString(NString start, NString end) {
        super(start, end);
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
        // create a graph
        StructuredGraph sg = new SgEmpty();

        // add nodes
        sg = new SgWithNodes(
                sg,
                new NString("a"),
                new NString("a1"),
                new NString("a2"),
                new NString("a3"),
                new NString("b1"),
                new NString("b2"),
                new NString("b3"),
                new NString("b")
        );
        
        // add edges
        sg = new SgWithEdges(
                sg,
                new EString("a", "a1"),
                new EString("a", "a2"),
                new EString("a", "a3"),
                new EString("a1", "b1"),
                new EString("a1", "b2"),
                new EString("a2", "b1"),
                new EString("a2", "b3"),
                new EString("a3", "b2"),
                new EString("a3", "b3"),
                new EString("b1", "b"),
                new EString("b2", "b"),
                new EString("b3", "b")
        );

        // prepare the graph for path finding
        NavigableGraph g = new NgFromStructure(sg);
        System.out.println(g);
        
        // Rock'n'roll!
        System.out.println(new PftNaive<>(g).path(
                new NString("a"), 
                new NString("b2")
        ));
    }
}