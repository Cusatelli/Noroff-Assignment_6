package com.access_and_expose.noroffassignment_6.view;

import com.access_and_expose.noroffassignment_6.data.repository.CustomerRepository;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(value = "/customer")
public class CustomerView {

    private final CustomerRepository customerRepository;

    public CustomerView(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/list")
    public String view(Model model) {
        model.addAttribute("customers", customerRepository.getAll());
        return "customerList";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerAdd";
    }

    @PostMapping("/add")
    public String addForm(@ModelAttribute Customer customer, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        customerRepository.add(customer);
        return "customerAdd";
    }
}
