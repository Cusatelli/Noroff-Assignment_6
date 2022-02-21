package com.access_and_expose.noroffassignment_6.dataaccess.repository;

import com.access_and_expose.noroffassignment_6.dataaccess.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CustomerRepository {

    ArrayList<Customer> getAllCustomers();
    Customer getCustomerById(Long Id);
    ArrayList<Customer> getCustomerByName(String firstName);
    ArrayList<Customer> getCustomerByFullName(String firstName, String lastName);



}
