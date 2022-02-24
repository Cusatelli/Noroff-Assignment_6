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

    /**
     * Constructor injected with services.
     * @param customerService Customer service.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Get all Customer objects from SQL Database Chinook using SQL Queries.
     * @param offset start getting all elements from offset value - default to 0
     * @param limit end getting all elements at offset + limit.
     * @return Collection of Customer model objects.
     */
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

    /**
     * Add new Customer to SQL Database Chinook using SQL Queries.
     * @param customer Customer model Object.
     * @return the Customer Model Object inserted.
     */
    @PostMapping(path = "/")
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return this.customerService.add(customer);
    }

    /**
     * Get Customer by id using SQL Queries from SQL Database Chinook.
     * @param customerId id to search for in Database.
     * @return Customer model object.
     */
    @GetMapping(value = "/id={customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return this.customerService.getById(customerId);
    }

    /**
     * Get Customer by name using SQL Queries from SQL Database Chinook.
     * @param firstName name to search for in Database.
     * @return Customer model object.
     */
    @GetMapping(value = "/firstName={firstName}")
    public Collection<Customer> getCustomerByName(@PathVariable String firstName) {
        return this.customerService.getByName(firstName);
    }

    /**
     * Update existing Customer to SQL Database Chinook using SQL Queries.
     * @param customerId id to search for in Database.
     * @param customer Customer model Object.
     * @return true if update successful.
     */
    @PutMapping(value = "/update={customerId}")
    public boolean updateExistingCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return this.customerService.update(customer); // TODO: use customer ID
    }

    /**
     * Delete Customer in SQL Database Chinook using SQL Queries.
     * @param customerId id to delete.
     * @return true if delete successful.
     */
    @DeleteMapping(value = "/delete={customerId}")
    public boolean deleteCustomer(@PathVariable String customerId) {
        return this.customerService.deleteById(customerId);
    }

    /**
     * Get Customer & Sort by Country from SQL Database using SQL Queries.
     * @param order Ascending or Descending order.
     * @return LinkedHashMap<String, Integer> ordered by insertion.
     */
    @GetMapping(value = "/sort/country")
    public LinkedHashMap<String, Integer> getCustomerByCountry(@RequestParam(required = false, defaultValue = "DESCENDING") Order order) {
        return this.customerService.sortByCountry(order);
    }

    /**
     * Get Customer & Sort by Country from SQL Database using SQL Queries.
     * @param order Ascending or Descending order.
     * @return LinkedHashMap<String, Integer> ordered by insertion.
     */
    @GetMapping(value = "/sort/spender")
    public LinkedHashMap<Customer, Integer> getCustomerBySpender(@RequestParam(required = false, defaultValue = "DESCENDING") Order order) {
        return this.customerService.getSpender(order);
    }

    /**
     * Get Customer Favorite Genre base on their invoice history from SQL Database using SQL Queries.
     * @param customerId Customer id.
     * @param order Ascending or Descending order.
     * @return LinkedHashMap<String, Integer> ordered by insertion.
     */
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
