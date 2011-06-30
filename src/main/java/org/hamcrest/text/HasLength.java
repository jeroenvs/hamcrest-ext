package org.hamcrest.text;

import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

/**
 * Check if a text matches some length.
 * 
 * @author Jeroen van Schagen
 * @since 30-06-2011
 */
public class HasLength extends FeatureMatcher<CharSequence, Integer> {

    /**
     * Construct a new {@link HasLength}.
     * @param lengthMatcher matches the text length
     */
    public HasLength(Matcher<? super Integer> lengthMatcher) {
        super(lengthMatcher, "a string with length", "string length");
    }
    
    @Factory
    public static HasLength length(Matcher<? super Integer> lengthMatcher) {
        return new HasLength(lengthMatcher);
    }
    
    @Factory
    public static HasLength lengthIs(Integer length) {
        return length(equalTo(length));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer featureValueOf(CharSequence value) {
        return value.length();
    }

}
