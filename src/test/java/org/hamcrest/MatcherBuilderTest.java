package org.hamcrest;

import static org.hamcrest.MatcherBuilder.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Unit tests for the {@link MatcherBuilder}.
 *
 * @author Jeroen van Schagen
 * @since 20-03-2011
 */
public class MatcherBuilderTest {

    /**
     * Assert that our regular matcher logic is maintained.
     */
    @Test
    public void testMatcher() {
        assertThat(2, is(greaterThan(1)));
        assertThat(1, not(is(greaterThan(1))));
    }
    
    /**
     * Assert that matchers can be combined using a logical AND.
     */
    @Test
    public void testAnd() {
        MatcherBuilder<Integer> betweenOneAndThree = is(greaterThan(1)).and(lessThan(3));
        assertThat(2, betweenOneAndThree);
        assertThat(1, not(betweenOneAndThree));
        assertThat(3, not(betweenOneAndThree));
    }

    /**
     * Assert that matchers can be combined using a logical OR.
     */
    @Test
    public void testOr() {
        MatcherBuilder<Integer> greaterThanOneOrNegative = is(greaterThan(1)).or(lessThan(0));
        assertThat(2, greaterThanOneOrNegative);
        assertThat(-1, greaterThanOneOrNegative);
        assertThat(1, not(greaterThanOneOrNegative));
    }

    /**
     * Assert that a matcher can be negated.
     */
    @Test
    public void testNot() {
        MatcherBuilder<? super Integer> notGreaterThanOne = is(greaterThan(1)).not();
        assertThat(1, notGreaterThanOne);
        assertThat(2, not(notGreaterThanOne)); // not + not = true
    }

}
