package com.access_and_expose.noroffassignment_6.dataaccess.controller;

import com.access_and_expose.noroffassignment_6.dataaccess.CustomerDAO;
import com.access_and_expose.noroffassignment_6.dataaccess.model.Customer;
import com.access_and_expose.noroffassignment_6.dataaccess.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="api/v1/customer", method = RequestMethod.GET)
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }



}
