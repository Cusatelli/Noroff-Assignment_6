package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.data.customer.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "api/v1/", method = RequestMethod.GET)
public class CustomerController {
    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "customers")
    public Collection<Customer> getAllCustomers() {
        return this.customerRepository.getAllCustomers();
    }

    @GetMapping(value = "customers/range/")
    public Collection<Customer> getCustomersInRange(String offset, String limit) {
        return this.customerRepository.getCustomersInRange(offset, limit);
    }

    @PostMapping(value = "customer")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return null;
    }

    @GetMapping(value = "customer/id={customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return this.customerRepository.getCustomerById(customerId);
    }

    @GetMapping(value = "customer/firstName={firstName}")
    public Collection<Customer> getCustomerByName(@PathVariable String firstName) {
        return this.customerRepository.getCustomerByName(firstName);
    }

    /*@PutMapping(value = "customer/{customerId}")
    public Customer updateExistingCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return null;
    }

    @DeleteMapping(value = "customer/{customerId}")
    public boolean deleteCustomer(@PathVariable String customerId) {
        return false;
    }*/
}
