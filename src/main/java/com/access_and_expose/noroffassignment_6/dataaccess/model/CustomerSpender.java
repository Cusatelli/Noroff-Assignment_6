package com.access_and_expose.noroffassignment_6.dataaccess.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@Table
public class CustomerSpender {

    private String customerExpenses;

    public CustomerSpender(String customerExpenses) {
        this.customerExpenses = customerExpenses;
    }
}
