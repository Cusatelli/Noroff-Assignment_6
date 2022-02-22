package com.access_and_expose.noroffassignment_6.data.customer;

import com.access_and_expose.noroffassignment_6.data.SQLiteConnectionHelper;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.model.customer.CustomerCountry;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CustomerRepository implements ICustomerRepository {

    @Override
    public ArrayList<Customer> getAllCustomers() {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer";
        return getCustomers(SQLQuery);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId = ?";
        return getCustomers(SQLQuery, customerId).get(0);
    }

    @Override
    public ArrayList<Customer> getCustomerByName(String firstName) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ?";
        return getCustomers(SQLQuery, firstName);
    }

    private ArrayList<Customer> getCustomers(String SQLQuery, String... params) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

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
    public Customer addNewCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateExistingCustomer(Customer customer) {
        return null;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public Customer getCustomerHighestSpender() {
        return null;
    }

    @Override
    public HashMap<CustomerCountry, Integer> getCustomersInCountry() {
        return null;
    }
}
