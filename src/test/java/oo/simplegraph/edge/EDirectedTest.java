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

import javaslang.collection.List;
import javaslang.control.Option;
import oo.simplegraph.node.NUnique;
import oo.simplegraph.node.NValue;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class EDirectedTest {
    @Test
    public final void basicEquality() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        EDirected edge = new EDirected(a1, a2);
        
        assertThat(
                edge
        ).isEqualTo(
                edge
        );
        assertThat(
                edge
        ).isNotEqualTo(
                null
        );
        assertThat(
                edge
        ).isNotEqualTo(
                new Object()
        );
    }

    @Test
    public final void comparesNodesBySystemIdentity() {
        final NUnique o1 = new NUnique();
        final NUnique o2 = new NUnique();

        assertThat(
                new EDirected(o1, o2)
        ).isEqualTo(
                new EDirected(o1, o2)
        );
        assertThat(
                new EDirected(o1, o2).hashCode()
        ).isEqualTo(
                new EDirected(o1, o2).hashCode()
        );
    }
    
    @Test
    public final void comparesNodesBySystemIdentity2() {
        final NUnique o1 = new NUnique();
        final NUnique o2 = new NUnique();
        final NUnique o3 = new NUnique();
        final NUnique o4 = new NUnique();

        assertThat(
                new EDirected(o1, o2)
        ).isNotEqualTo(
                new EDirected(o3, o4)
        );
        assertThat(
                new EDirected(o1, o2).hashCode()
        ).isNotEqualTo(
                new EDirected(o3, o4).hashCode()
        );
        assertThat(
                new EDirected(o1, o2)
        ).isNotEqualTo(
                new EDirected(o1, o3)
        );
        assertThat(
                new EDirected(o1, o2).hashCode()
        ).isNotEqualTo(
                new EDirected(o1, o3).hashCode()
        );
    }

    @Test
    public final void distinctsDirection() {
        final NUnique o1 = new NUnique();
        final NUnique o2 = new NUnique();
        assertThat(
                new EDirected(o1, o2)
        ).isNotEqualTo(
                new EDirected(o2, o1)
        );
    }

    @Test
    public final void allowsFollowingToDirection() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        assertThat(
                new EDirected(a1, a2).follow(a1)
        ).isEqualTo(
                Option.of(a2)
        );
    }
    
    @Test
    public final void prohibitsFollowingAgainstDirection() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        assertThat(
                new EDirected(a1, a2).follow(a2)
        ).isEqualTo(
                Option.none()
        );
    }
    
    @Test
    public final void hasOnlyOneNode() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        assertThat(
                new EDirected(a1, a2).startingNodes()
        ).containsOnly(
                a1
        );
    }
}
