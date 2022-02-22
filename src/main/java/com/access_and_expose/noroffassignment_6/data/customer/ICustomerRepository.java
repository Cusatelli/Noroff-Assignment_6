package com.access_and_expose.noroffassignment_6.data.customer;

import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.model.customer.CustomerCountry;
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
