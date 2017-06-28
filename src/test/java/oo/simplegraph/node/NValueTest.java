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
package oo.simplegraph.node;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

/**
 *
 * @author Kapralov Sergey
 */
public class NValueTest {
    @Test
    public final void basicEquality() {
        NValue node = new NValue(
                new Object()
        );
        
        assertThat(
                node
        ).isEqualTo(
                node
        );
        assertThat(
                node
        ).isNotEqualTo(
                null
        );
        assertThat(
                node
        ).isNotEqualTo(
                new Object()
        );
    }
    
    @Test
    public final void holdsValue() {
        NValue node = new NValue(
                "hello"
        );
        
        assertThat(
                node.value()
        ).isEqualTo(
                "hello"
        );
    }
    
    @Test
    public final void equalsByValue() {
        UUID o1 = UUID.fromString("240e6411-e252-4ada-a216-446655440223");
        UUID o2 = UUID.fromString("240e6411-e252-4ada-a216-446655440223");
        UUID o3 = UUID.fromString("ae310024-5aa1-cbd6-d22b-ba3311241124");
        
        assertThat(
                new NValue(o1)
        ).isEqualTo(
                new NValue(o2)
        );
        assertThat(
                new NValue(o1)
        ).isNotEqualTo(
                new NValue(o3)
        );
    }
}
