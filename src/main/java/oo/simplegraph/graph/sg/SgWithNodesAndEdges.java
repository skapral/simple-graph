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
import javaslang.collection.Set;
import oo.simplegraph.edge.Edge;
import oo.simplegraph.node.Node;



class SgWithNodesAndEdgesInference<ND extends Node<?>, ED extends Edge<ND, ED>> implements StructuredGraph.Inference<ND, ED> {
    private final StructuredGraph<ND, ED> sg;
    private final Set<ND> nodes;
    private final Set<ED> edges;

    public SgWithNodesAndEdgesInference(StructuredGraph<ND, ED> sg, Set<ND> nodes, Set<ED> edges) {
        this.sg = sg;
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    public StructuredGraph<ND, ED> graph() {
        return new StructuredGraph<ND, ED>() {
            @Override
            public Set<ND> nodes() {
                return sg.nodes().addAll(nodes).addAll(edges.flatMap(Edge::nodes));
            }

            @Override
            public Set<ED> edges() {
                return sg.edges().addAll(edges);
            }
        };
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(this.sg, this.nodes, this.edges);
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof SgWithNodesAndEdgesInference) {
            final SgWithNodesAndEdgesInference<ND, ED> other = (SgWithNodesAndEdgesInference<ND, ED>) obj;
            return Objects.equals(this.sg, other.sg) &&
                   Objects.equals(this.nodes, other.nodes) &&
                   Objects.equals(this.edges, other.edges);
        } else {
            return false;
        }
    }

    @Override
    public final String toString() {
        return "SgWithNodesAndEdgesInference{" + "sg=" + sg + ", nodes=" + nodes + ", edges=" + edges + '}';
    }
}


/**
 *
 * @author Kapralov Sergey
 */
public class SgWithNodesAndEdges<ND extends Node<?>, ED extends Edge<ND, ED>> extends SgInferred<ND, ED> implements StructuredGraph<ND, ED> {
    public SgWithNodesAndEdges(StructuredGraph<ND, ED> sg, Set<ND> nodes, Set<ED> edges) {
        super(new SgWithNodesAndEdgesInference<>(sg, nodes, edges));
    }
}
