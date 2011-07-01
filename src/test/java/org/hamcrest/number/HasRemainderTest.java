package org.hamcrest.number;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.number.HasRemainder.dividableBy;
import static org.hamcrest.number.HasRemainder.remainderBy;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

public class HasRemainderTest {

    /**
     * 4 can be divided by 2, resulting in a 0 remainder.
     */
    @Test
    public void testCanDivideInteger() {
        assertThat(4, dividableBy(2));
    }
    
    /**
     * 5 cannot be divided by 2.
     */
    @Test
    public void testCannotDivideInteger() {
        assertThat(5, not(dividableBy(2)));
    }
    
    /**
     * Whenever we divide 4.1 by 2, we obtain a remainder of 0.1.
     */
    @Test
    public void testRemainderOfDecimal() {
        assertThat(4.1, remainderBy(2, equalTo(new BigDecimal("0.1"))));
    }
    
}
