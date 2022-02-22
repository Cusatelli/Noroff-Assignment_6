package com.access_and_expose.noroffassignment_6.model;

import org.springframework.data.relational.core.mapping.Table;

@Table
public class CustomerSpender {

    private String customerExpenses;

    public CustomerSpender(String customerExpenses) {
        this.customerExpenses = customerExpenses;
    }

    public String getCustomerExpenses() {
        return customerExpenses;
    }

    public void setCustomerExpenses(String customerExpenses) {
        this.customerExpenses = customerExpenses;
    }

    //TODO: add getters and setters
}
