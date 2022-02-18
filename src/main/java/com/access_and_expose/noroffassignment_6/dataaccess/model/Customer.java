package com.access_and_expose.noroffassignment_6.dataaccess.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
@Table
@NoArgsConstructor
public class Customer {
    @javax.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private int phone;
    private String email;
    private String country;
    private int postalCode;

    public Customer(Long id, String firstName, String lastName, int phone, String email, String country, int postalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
