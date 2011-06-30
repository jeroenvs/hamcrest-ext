package org.hamcrest.text;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.HasPattern.pattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.StringDescription;
import org.junit.Test;

public class HasPatternTest {
    private static final String LETTERS_REGEX = "[a-zA-Z]+";

    @Test
    public void testMatching() {
        assertThat("abcdef", pattern(LETTERS_REGEX));
    }
    
    @Test
    public void testNotMatching() {
        assertThat("abc^%@def", not(pattern(LETTERS_REGEX)));
    }
    
    @Test
    public void testDescribeTo() {
        StringDescription description = new StringDescription();
        pattern(LETTERS_REGEX).describeTo(description);
        assertEquals("hasPattern([a-zA-Z]+)", description.toString());
    }
    
}
