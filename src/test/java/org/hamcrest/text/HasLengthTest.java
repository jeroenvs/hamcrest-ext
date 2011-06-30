package org.hamcrest.text;

import static org.hamcrest.text.HasLength.hasLengthOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HasLengthTest {

    @Test
    public void testMatchLength() {
        final String text = "abcdef";
        assertThat(text, hasLengthOf(6));
    }
    
}
