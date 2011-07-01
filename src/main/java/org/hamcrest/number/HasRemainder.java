package org.hamcrest.number;

import static org.hamcrest.core.IsEqual.equalTo;

import java.math.BigDecimal;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class HasRemainder extends TypeSafeMatcher<Number> {
    private final Matcher<BigDecimal> remainderMatcher;
    private final BigDecimal divisorDecimal;
    
    public HasRemainder(Number divisor, Matcher<BigDecimal> remainderMatcher) {
        this.remainderMatcher = remainderMatcher;
        this.divisorDecimal = new BigDecimal(divisor.toString());
    }
    
    @Factory
    public static HasRemainder remainderBy(Number divisor, Matcher<BigDecimal> remainderMatcher) {
        return new HasRemainder(divisor, remainderMatcher);
    }

    @Factory
    public static HasRemainder dividableBy(Number divisor) {
        return remainderBy(divisor, equalTo(new BigDecimal(0)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean matchesSafely(Number dividend) {
        return remainderMatcher.matches(remainder(dividend));
    }
    
    protected BigDecimal remainder(Number divident) {
        return new BigDecimal(divident.toString()).remainder(divisorDecimal);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        description
            .appendText("canDivideBy(")
            .appendValue(divisorDecimal)
            .appendText(", ")
            .appendDescriptionOf(remainderMatcher)
            .appendText(")");
    }

}
