package org.hamcrest;

import com.mysema.query.types.Path;

/**
 * Matches the current value of a specific path.
 * 
 * @author Jeroen van Schagen
 * @since 30-06-2011
 *
 * @param <T> type of the path root
 * @param <P> type of value being matched
 */
public class PathMatcher<T,P> extends TypeSafeDiagnosingMatcher<T> {   
    private static final PathTraverser PATH_TRAVERSER = new PathTraverser();
    
    private final Path<P> path;
    private final Matcher<? super P> valueMatcher;
    
    /**
     * Construct a new {@link PathMatcher}.
     * @param path the path to our value
     * @param valueMatcher used to match the value
     */
    public PathMatcher(Path<P> path, Matcher<? super P> valueMatcher) {
        this.path = path;
        this.valueMatcher = valueMatcher;
    }
    
    @Factory
    public static <T,P> Matcher<T> valueOf(Path<P> path, Matcher<? super P> matcher) {
        return new PathMatcher<T,P>(path, matcher);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean matchesSafely(T item, Description mismatchDescription) {
        P propertyValue = PATH_TRAVERSER.traverse(path, item);
        boolean valueMatches = valueMatcher.matches(propertyValue);
        if (!valueMatches) {
            mismatchDescription.appendText("property \"" + path.toString() + "\" ");
            valueMatcher.describeMismatch(propertyValue, mismatchDescription);
        }
        return valueMatches;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void describeTo(Description description) {
        description.appendText("valueOf(");
        description.appendValue(path.toString());
        description.appendText(", ");
        description.appendDescriptionOf(valueMatcher);
        description.appendText(")");
    }
    
}
