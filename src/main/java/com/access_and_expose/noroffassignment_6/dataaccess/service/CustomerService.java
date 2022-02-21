package com.access_and_expose.noroffassignment_6.dataaccess.service;

import com.access_and_expose.noroffassignment_6.dataaccess.model.Customer;
import com.access_and_expose.noroffassignment_6.dataaccess.model.CustomerCountry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public List<Customer> getCustomers() {
        return List.of(
                new Customer(0L, "John", "Doe", 0721234567, "johndoe@mail.com", new CustomerCountry("UK","AA99AA")),
                new Customer(1L, "Jane", "Doe", 0723264560, "janedoe@mail.com",new CustomerCountry("UK","AA9AA"))
        );
    }
}
