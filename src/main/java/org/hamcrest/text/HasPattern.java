package org.hamcrest.text;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

/**
 * Check if a text matches some regex pattern.
 * 
 * @author Jeroen van Schagen
 * @since 30-06-2011
 */
public class HasPattern extends TypeSafeMatcher<CharSequence> {
    private final Pattern pattern;
    
    public HasPattern(Pattern pattern) {
        if(pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        this.pattern = pattern;
    }
    
    @Factory
    public static HasPattern matchesPattern(Pattern pattern) {
        return new HasPattern(pattern);
    }
    
    @Factory
    public static HasPattern matchesPattern(String regex) {
        return matchesPattern(Pattern.compile(regex));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean matchesSafely(CharSequence text) {
        return pattern.matcher(text).matches();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("hasPattern(").appendText(pattern.pattern()).appendText(")");
    }

}
