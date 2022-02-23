package com.access_and_expose.noroffassignment_6.data.repository.customer;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

@Repository
public interface ICustomerRepository extends RestRepository<Customer> {
    LinkedHashMap<String, Integer> sortByCountry();
    LinkedHashMap<Customer, Integer> getCustomerHighestSpender();
}