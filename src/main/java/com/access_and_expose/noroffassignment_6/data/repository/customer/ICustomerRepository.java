package com.access_and_expose.noroffassignment_6.data.repository.customer;

import com.access_and_expose.noroffassignment_6.data.repository.RestRepository;
import com.access_and_expose.noroffassignment_6.model.Genre;
import com.access_and_expose.noroffassignment_6.model.Order;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;

@Repository
public interface ICustomerRepository extends RestRepository<Customer> {
    LinkedHashMap<String, Integer> sortByCountry(Order order);
    LinkedHashMap<Customer, Integer> getSpender(Order order);
    LinkedHashMap<Genre, Integer> getFavoriteGenre(String customerId);
}