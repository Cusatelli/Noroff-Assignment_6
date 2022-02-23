package com.access_and_expose.noroffassignment_6.model.customer;

public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private int phone;
    private String email;
    private CustomerCountry customerCountry;

    public Customer() {}
    public Customer(Long id, String firstName, String lastName, int phone, String email, CustomerCountry customerCountry) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.customerCountry = customerCountry;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String name) {
        this.firstName = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String surname) {
        this.lastName = surname;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phoneNumber) {
        this.phone = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public CustomerCountry getCustomerCountry() {
        return customerCountry;
    }
    public void setCustomerCountry(CustomerCountry customerCountry) {
        this.customerCountry = customerCountry;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", customerCountry=" + customerCountry +
                '}';
    }
}