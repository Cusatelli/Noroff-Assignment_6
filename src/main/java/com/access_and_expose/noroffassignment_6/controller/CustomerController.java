package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.data.repository.customer.ICustomerRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedHashMap;

@RestController
@Tag(name = "Customer")
@RequestMapping(value = "api/v1/customer")
public class CustomerController {

    private final ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/")
    public Collection<Customer> getAllCustomers(
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit
    ) {
        try {
            return this.customerRepository.getAll(
                    Integer.parseInt(offset),
                    Integer.parseInt(limit)
            );
        } catch (NumberFormatException numberFormatException) {
            return this.customerRepository.getAll();
        }
    }

    @PostMapping(path = "/")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return this.customerRepository.add(customer);
    }

    @GetMapping(value = "/id={customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return this.customerRepository.getById(customerId);
    }

    @GetMapping(value = "/firstName={firstName}")
    public Collection<Customer> getCustomerByName(@PathVariable String firstName) {
        return this.customerRepository.getByName(firstName);
    }

    @PutMapping(value = "/update={customerId}")
    public boolean updateExistingCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return this.customerRepository.update(customer); // TODO: use customer ID
    }

    @DeleteMapping(value = "/delete={customerId}")
    public boolean deleteCustomer(@PathVariable String customerId) {
        return this.customerRepository.deleteById(customerId);
    }

    @GetMapping(value = "/sortByCountry")
    public LinkedHashMap<String, Integer> getCustomerOrderedByCountry() {
        return this.customerRepository.sortByCountry();
    }

    @GetMapping(value = "/sortByHighestSpender")
    public LinkedHashMap<Customer, Integer> getCustomerByHighestSpender() {
        return this.customerRepository.getCustomerHighestSpender();
    }
}