package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.data.customer.ICustomerRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@Tag(name = "Customer")
@RequestMapping(value = "api/v1")
public class CustomerController {

    private final ICustomerRepository customerRepository;

    public CustomerController(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customer")
    public Collection<Customer> getAllCustomers(
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit
    ) {
        try {
            return this.customerRepository.getAllInRange(
                    Integer.parseInt(offset),
                    Integer.parseInt(limit)
            );
        } catch (NumberFormatException numberFormatException) {
            return this.customerRepository.getAll();
        }
    }

    @PostMapping(path = "/customer")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return this.customerRepository.add(customer);
    }

    @GetMapping(value = "/customer/id={customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return this.customerRepository.getById(customerId);
    }

    @GetMapping(value = "/customer/firstName={firstName}")
    public Collection<Customer> getCustomerByName(@PathVariable String firstName) {
        return this.customerRepository.getByName(firstName);
    }

    @PutMapping(value = "customer/update={customerId}")
    public Customer updateExistingCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return this.customerRepository.update(customer); // TODO: use customer ID
    }

    @DeleteMapping(value = "customer/delete={customerId}")
    public boolean deleteCustomer(@PathVariable String customerId) {
        return this.customerRepository.delete(customerId);
    }
}