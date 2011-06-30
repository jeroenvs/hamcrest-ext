package org.hamcrest.text;

import static org.hamcrest.text.HasLength.lengthIs;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HasLengthTest {

    @Test
    public void testMatchLength() {
        final String text = "abcdef";
        assertThat(text, lengthIs(6));
    }
    
}
