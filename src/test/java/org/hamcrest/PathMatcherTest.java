package org.hamcrest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.PathMatcher.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.domain.Address;
import org.hamcrest.domain.Customer;
import org.hamcrest.domain.QCustomer;
import org.junit.Test;

import com.mysema.query.types.Path;

public class PathMatcherTest {
    private static final Path<String> STREET_PATH = QCustomer.customer.address.street;

    @Test
    public void testMatchValue() {
        Customer customer = new Customer();
        Address address = new Address();
        address.setCity("bla");
        address.setStreet("blaway 142");
        customer.setAddress(address);

        assertThat(customer, valueOf(STREET_PATH, equalTo("blaway 142")));
    }
    
    @Test
    public void testMatchWithNullElement() {
        Customer customer = new Customer();
        assertNull(customer.getAddress());

        assertThat(customer, valueOf(STREET_PATH, nullValue()));
    }
    
    @Test
    public void testDescribeTo() {
        StringDescription description = new StringDescription();
        valueOf(STREET_PATH, equalTo("x")).describeTo(description);
        assertEquals("valueOf(\"customer.address.street\", \"x\")", description.toString());
    }
    
}
