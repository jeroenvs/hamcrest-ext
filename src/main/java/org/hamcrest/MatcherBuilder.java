package org.hamcrest;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Matcher that simplifies the merging with additional matchers. By using
 * this matcher, composite matching logic can be composed, following an
 * intuitive and simple programming interface:
 *
 * <pre>
 *  that(is(simple())).and(intuitive());
 * </pre>
 *
 * @author Jeroen van Schagen
 * @since 20-03-2011
 *
 * @param <T> type of elements being matched on
 */
public class MatcherBuilder<T> extends TypeSafeMatcher<T> {
    private final Matcher<? super T> matcher;

    /**
     * Construct a new {@link MatcherBuilder}.
     * @param matcher initial matcher that should be used
     */
    private MatcherBuilder(Matcher<? super T> matcher) {
        if (matcher == null) {
            throw new IllegalArgumentException("Cannot construct a matcher builder, using an initial null matcher.");
        }
        this.matcher = matcher;
    }

    /**
     * Construct a new {@link MatcherBuilder}.
     * @param <T> type of elements being matched on
     * @param matcher initial matcher that should be used
     * @return new matcher builder, based on our initial matcher
     */
    public static <T> MatcherBuilder<T> is(Matcher<? super T> matcher) {
        return builder(matcher); // Results in more readable matcher compositions
    }

    /**
     * Construct a new {@link MatcherBuilder}.
     * @param <T> type of elements being matched on
     * @param matcher initial matcher that should be used
     * @return new matcher builder, based on our initial matcher
     */
    @Factory
    public static <T> MatcherBuilder<T> builder(Matcher<? super T> matcher) {
        return new MatcherBuilder<T>(matcher);
    }

    /**
     * Combine the current matcher logic, with that of another matcher. Our
     * new matcher will only be satisfied, when both matchers are satisfied.
     * @param other another matcher we should combine with
     * @return new matcher, which combines the logic of both matchers
     */
    public MatcherBuilder<T> and(Matcher<? super T> other) {
        return new MatcherBuilder<T>(Matchers.allOf(combineMatcherWith(other)));
    }

    /**
     * Create a collection of our current matcher, and another matcher.
     * @param other the other matcher we should keep in our collection
     * @return collection of our current matcher, and another matcher
     */
    private Collection<Matcher<? super T>> combineMatcherWith(Matcher<? super T> other) {
        Collection<Matcher<? super T>> matchers = new ArrayList<Matcher<? super T>>();
        matchers.add(matcher);
        matchers.add(other);
        return matchers;
    }

    /**
     * Combine the current matcher logic, with that of another matcher. Our
     * new matcher will only be satisfied, when one of the matchers is satisfied.
     * @param other another matcher we should combine with
     * @return new matcher, which combines the logic of both matchers
     */
    public MatcherBuilder<T> or(Matcher<? super T> other) {
        return new MatcherBuilder<T>(Matchers.anyOf(combineMatcherWith(other)));
    }

    /**
     * Construct a new matcher that negates the logic of our current matcher.
     * @return new matcher, which negates the logic of our current matcher
     */
    public MatcherBuilder<T> not() {
        return new MatcherBuilder<T>(Matchers.not(matcher));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean matchesSafely(T item) {
        return matcher.matches(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        matcher.describeTo(description);
    }

}
