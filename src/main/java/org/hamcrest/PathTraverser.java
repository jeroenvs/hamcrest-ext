package org.hamcrest;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mysema.query.types.Path;

/**
 * Traverses a Query DSL {@link Path}, retrieving the current value.
 * 
 * @author Jeroen van Schagen
 * @since 30-06-2011
 */
public class PathTraverser {

    /**
     * Retrieve the current value of a path.
     * 
     * @param <T> type of value being returned
     * @param path path to the desired value
     * @param container root object, from where our path starts
     * @return current path value
     */
    @SuppressWarnings("unchecked")
    public <T> T traverse(Path<T> path, Object container) {
        // Retrieve the object that contains our property directly
        if(!path.getMetadata().isRoot()) {
            container = traverse(path.getMetadata().getParent(), container);
        }

        Object result = null;
        if(container != null) { // Cannot traverse from a null pointer
            switch(path.getMetadata().getPathType()) {
            case PROPERTY:
                // Access the property from public getter method
                String propertyName = path.getMetadata().getExpression().toString();
                result = readProperty(propertyName, container);
                break;
            case VARIABLE:
                // Root element is already provided, do nothing
                result = container;
                break;
            default:
                throw new UnsupportedOperationException();
            }
        }
        return (T) result;
    }
    
    /**
     * Read the current property value from an object.
     * 
     * @param propertyName name of the property
     * @param container object that contains the property
     * @return current property value
     */
    protected Object readProperty(String propertyName, Object container) {       
        PropertyDescriptor property;
        try {
            property = new PropertyDescriptor(propertyName, container.getClass());
        } catch (IntrospectionException e) {
            throw new IllegalStateException(
                String.format("Object [%s] does not have a property '%s'",container.getClass(), propertyName)
            );
        }
        
        Method readMethod = property.getReadMethod();
        if(readMethod == null) {
            throw new IllegalStateException(
               String.format("Property '%s' in [%s] is not readable", propertyName, container.getClass())
            );
        }
        
        try {
            return readMethod.invoke(container);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
}
