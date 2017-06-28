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
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class EBiDirectedTest {
    @Test
    public final void equality() {
        EBiDirected edge = new EBiDirected(
                new NUnique(), 
                new NUnique()
        );
        
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
                new EBiDirected(o1, o2)
        ).isEqualTo(
                new EBiDirected(o1, o2)
        );
        assertThat(
                new EBiDirected(o1, o2).hashCode()
        ).isEqualTo(
                new EBiDirected(o1, o2).hashCode()
        );
    }

    @Test
    public final void comparesNodesBySystemIdentity2() {
        final NUnique o1 = new NUnique();
        final NUnique o2 = new NUnique();
        final NUnique o3 = new NUnique();
        final NUnique o4 = new NUnique();

        assertThat(
                new EBiDirected(o1, o2)
        ).isNotEqualTo(
                new EBiDirected(o3, o4)
        );
        assertThat(
                new EBiDirected(o1, o2).hashCode()
        ).isNotEqualTo(
                new EBiDirected(o3, o4).hashCode()
        );
        assertThat(
                new EBiDirected(o1, o2)
        ).isNotEqualTo(
                new EBiDirected(o1, o3)
        );
        assertThat(
                new EBiDirected(o1, o2).hashCode()
        ).isNotEqualTo(
                new EBiDirected(o1, o3).hashCode()
        );
    }

    @Test
    public final void ignoresDirection() {
        final NUnique o1 = new NUnique();
        final NUnique o2 = new NUnique();
        assertThat(
                new EBiDirected(o1, o2)
        ).isEqualTo(
                new EBiDirected(o2, o1)
        );
    }

    @Test
    public final void allowsFollowingToDirection() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        assertThat(
                new EBiDirected(a1, a2).follow(a1)
        ).isEqualTo(
                Option.of(a2)
        );
    }
    
    @Test
    public final void allowsFollowingAgainstDirection() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        assertThat(
                new EBiDirected(a1, a2).follow(a2)
        ).isEqualTo(
                Option.of(a1)
        );
    }
    
    @Test
    public final void hasBothNodes() {
        final NUnique a1 = new NUnique();
        final NUnique a2 = new NUnique();
        assertThat(
                new EBiDirected(a1, a2).nodes()
        ).containsAll(
                List.of(a1, a2)
        );
    }
}
