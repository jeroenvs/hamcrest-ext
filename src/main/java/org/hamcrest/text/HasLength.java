package org.hamcrest.text;

import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

public class HasLength extends FeatureMatcher<CharSequence, Integer> {

    /**
     * Construct a new {@link HasLength}.
     * @param lengthMatcher matches the text length
     */
    public HasLength(Matcher<? super Integer> lengthMatcher) {
        super(lengthMatcher, "a string with length", "string length");
    }
    
    @Factory
    public static HasLength hasLength(Matcher<? super Integer> lengthMatcher) {
        return new HasLength(lengthMatcher);
    }
    
    @Factory
    public static HasLength hasLengthOf(Integer length) {
        return hasLength(equalTo(length));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer featureValueOf(CharSequence value) {
        return value.length();
    }

}
