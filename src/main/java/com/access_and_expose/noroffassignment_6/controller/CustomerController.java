package com.access_and_expose.noroffassignment_6.controller;

import com.access_and_expose.noroffassignment_6.data.service.CustomerService;
import com.access_and_expose.noroffassignment_6.model.Genre;
import com.access_and_expose.noroffassignment_6.model.Order;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Objects;

@RestController
@Tag(name = "Customer")
@RequestMapping(value = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public Collection<Customer> getAllCustomers(
            @RequestParam(value = "offset", defaultValue = "0", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit
    ) {
        try {
            return this.customerService.getAll(
                    Integer.parseInt(offset),
                    Integer.parseInt(limit)
            );
        } catch (NumberFormatException numberFormatException) {
            return this.customerService.getAll();
        }
    }

    @PostMapping(path = "/")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return this.customerService.add(customer);
    }

    @GetMapping(value = "/id={customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return this.customerService.getById(customerId);
    }

    @GetMapping(value = "/firstName={firstName}")
    public Collection<Customer> getCustomerByName(@PathVariable String firstName) {
        return this.customerService.getByName(firstName);
    }

    @PutMapping(value = "/update={customerId}")
    public boolean updateExistingCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return this.customerService.update(customer); // TODO: use customer ID
    }

    @DeleteMapping(value = "/delete={customerId}")
    public boolean deleteCustomer(@PathVariable String customerId) {
        return this.customerService.deleteById(customerId);
    }

    @GetMapping(value = "/sort/country")
    public LinkedHashMap<String, Integer> getCustomerByCountry(@RequestParam(required = false, defaultValue = "DESCENDING") Order order) {
        return this.customerService.sortByCountry(order);
    }

    @GetMapping(value = "/sort/spender")
    public LinkedHashMap<Customer, Integer> getCustomerBySpender(@RequestParam(required = false, defaultValue = "DESCENDING") Order order) {
        return this.customerService.getSpender(order);
    }

    @GetMapping(value = "/favorite/genre/id={customerId}")
    public LinkedHashMap<Genre, Integer> getCustomerFavoriteGenre(@PathVariable String customerId, @RequestParam(required = false, defaultValue = "DESCENDING") Order order) {
        LinkedHashMap<Genre, Integer> map = this.customerService.getFavoriteGenre(customerId, order);
        LinkedHashMap<Genre, Integer> newMap = new LinkedHashMap<>();

        Object[] array = map.values().toArray();

        if(!Objects.equals(array[0], array[1])) {
            for (Genre genre : map.keySet()) {
                newMap.put(genre, map.get(genre));
                break; // Exit immediately after assigning ONE (1) value.
            }
            return newMap;
        }

        return map;
    }
}
