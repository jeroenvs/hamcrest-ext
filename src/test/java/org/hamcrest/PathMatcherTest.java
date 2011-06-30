package org.hamcrest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.PathMatcher.valueOf;
import static org.junit.Assert.assertThat;

import org.hamcrest.domain.Address;
import org.hamcrest.domain.Customer;
import org.hamcrest.domain.QCustomer;
import org.junit.Test;

public class PathMatcherTest {

    @Test
    public void testPathEvaluate() {
        Customer customer = new Customer();
        Address address = new Address();
        address.setCity("bla");
        address.setStreet("blaway 142");
        customer.setAddress(address);

        assertThat(customer, valueOf(QCustomer.customer.address.street, equalTo("blaway 142")));
    }
    
}
