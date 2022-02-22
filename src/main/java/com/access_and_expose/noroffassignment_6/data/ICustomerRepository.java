package com.access_and_expose.noroffassignment_6.data;

import com.access_and_expose.noroffassignment_6.model.Customer;
import com.access_and_expose.noroffassignment_6.model.CustomerCountry;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public interface ICustomerRepository {
    Collection<Customer> getAllCustomers();
    Customer getCustomerById(String customerId);
    Collection<Customer> getCustomerByName(String firstName);
    Customer addNewCustomer(Customer customer);
    Customer updateExistingCustomer(Customer customer);
    boolean deleteCustomer(String customerId);
    Customer getCustomerHighestSpender();
    HashMap<CustomerCountry, Integer> getCustomersInCountry();
}
