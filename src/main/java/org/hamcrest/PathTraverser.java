package org.hamcrest;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hamcrest.beans.PropertyUtil;

import com.mysema.query.types.Path;

public class PathTraverser {

    @SuppressWarnings("unchecked")
    public <T> T traverse(Path<T> path, Object container) {
        // Retrieve the object that contains our property directly
        if(!path.getMetadata().isRoot()) {
            container = traverse(path.getMetadata().getParent(), container);
        }

        Object result = null;
       
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
        
        return (T) result;
    }
    
    protected Object readProperty(String propertyName, Object container) {
        if(container == null) {
            return null;
        }
        
        PropertyDescriptor property = PropertyUtil.getPropertyDescriptor(propertyName, container);
        if(property == null) {
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
