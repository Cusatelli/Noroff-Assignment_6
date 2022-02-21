package com.access_and_expose.noroffassignment_6.dataaccess.service;

import com.access_and_expose.noroffassignment_6.dataaccess.CustomerDAO;
import com.access_and_expose.noroffassignment_6.dataaccess.model.Customer;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    public List <Customer> getAllCustomers() {
        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.getAllCustomers();
    }
}
