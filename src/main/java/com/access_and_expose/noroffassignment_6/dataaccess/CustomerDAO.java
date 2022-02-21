package com.access_and_expose.noroffassignment_6.dataaccess;

import com.access_and_expose.noroffassignment_6.dataaccess.model.Customer;
import com.access_and_expose.noroffassignment_6.dataaccess.model.CustomerCountry;
import com.access_and_expose.noroffassignment_6.dataaccess.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerRepository {


    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:Chinook.sqlite")) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerCountry customerCountry = new CustomerCountry(resultSet.getString("Country"),resultSet.getString("PostalCode"));
                Customer customer = new Customer(
                        resultSet.getLong("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getInt("Phone"),
                        resultSet.getString("Email"),
                        customerCountry
                );
                customers.add(customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(Long Id) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByName(String firstName) {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomerByFullName(String firstName, String lastName) {
        return null;
    }
}

