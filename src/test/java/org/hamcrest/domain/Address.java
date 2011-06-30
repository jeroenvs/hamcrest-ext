package org.hamcrest.domain;

public class Address {
    private String street;
    private String city;
    
    public String getCity() {
        return city;
    }
    
    public String getStreet() {
        return street;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
}