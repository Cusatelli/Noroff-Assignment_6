package com.access_and_expose.noroffassignment_6.dataaccess.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Entity
@Table
public class CustomerCountry { // TODO: Add Country and Zip to Customer in array.

    @Id
    @GeneratedValue
    private Long id;
    private String countryName;
    private String postalCode;

    public CustomerCountry() {

    }
    public CustomerCountry(String countryName, String postalCode) {
        this.countryName = countryName;
        this.postalCode = postalCode;
    }
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @javax.persistence.Id
    public Long getId() {
        return id;
    }
}
