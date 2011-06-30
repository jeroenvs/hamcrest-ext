package org.hamcrest;

import static org.hamcrest.ComposableMatcher.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ComposableMatcherTest {

    /**
     * Matchers should still work when composed.
     */
    @Test
    public void testMatcher() {
        assertThat(2, is(greaterThan(1)));
        assertThat(1, not(is(greaterThan(1))));
    }
    
    /**
     * We can also combine matchers with "AND" conditional logic.
     */
    @Test
    public void testAnd() {
        Matcher<Integer> betweenOneAndThree = is(greaterThan(1)).and(lessThan(3));
        assertThat(2, betweenOneAndThree);
        assertThat(1, not(betweenOneAndThree));
        assertThat(3, not(betweenOneAndThree));
    }

    /**
     * We can also combine matchers with "OR" conditional logic.
     */
    @Test
    public void testOr() {
        Matcher<Integer> greaterThanOneOrNegative = is(greaterThan(1)).or(lessThan(0));
        assertThat(2, greaterThanOneOrNegative);
        assertThat(-1, greaterThanOneOrNegative);
        assertThat(1, not(greaterThanOneOrNegative));
    }

    /**
     * Matchers can also be negated.
     */
    @Test
    public void testNot() {
        Matcher<Integer> notGreaterThanOne = is(greaterThan(1)).not();
        assertThat(1, notGreaterThanOne);
        assertThat(2, not(notGreaterThanOne)); // not + not = true
    }

}
