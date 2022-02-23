package com.access_and_expose.noroffassignment_6.data.customer;

import com.access_and_expose.noroffassignment_6.data.SQLiteConnectionHelper;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.model.customer.CustomerCountry;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class CustomerRepository implements ICustomerRepository {

    @Override
    public Collection<Customer> getAll() {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer";
        return getCustomers(SQLQuery);
    }

    @Override
    public Collection<Customer> getAllInRange(@PathVariable int offset, @PathVariable int limit) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId BETWEEN ? AND ?";
        return getCustomers(SQLQuery, String.valueOf(offset), String.valueOf(offset + limit));
    }

    @Override
    public Customer getById(@PathVariable String customerId) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId = ?";
        return getCustomers(SQLQuery, customerId).get(0);
    }

    @Override
    public Collection<Customer> getByName(@PathVariable String firstName) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ?";
        return getCustomers(SQLQuery, firstName);
    }

    @Override
    public Customer add(@PathVariable Customer customer) {
        String SQLQuery = "INSERT INTO Customer(FirstName, LastName, Country, PostalCode, Phone, Email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCustomerCountry().getCountryName());
            preparedStatement.setString(4, customer.getCustomerCountry().getPostalCode());
            preparedStatement.setInt(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        customer.setId(Long.valueOf(generatedKeys.getString(1)));
                    }
                }

                connection.close();
                return customer;
            }
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer update(Customer item) {
        String SQLQuery = "UPDATE Customer SET FirstName = ?, LastName = ?, Phone = ?, Email = ?, Country = ?, PostalCode = ? WHERE CustomerId = ?";
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            preparedStatement.setString(1,item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.setInt(3, item.getPhone());
            preparedStatement.setString(4, item.getEmail());
            preparedStatement.setString(5, item.getCustomerCountry().getCountryName());
            preparedStatement.setString(6,item.getCustomerCountry().getPostalCode());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean delete(String id) {
        String SQLQuery = "DELETE FROM Customer WHERE CustomerID = ?";

        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            preparedStatement.setString(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer getCustomerHighestSpender() {
        return null;
    }

    @Override
    public HashMap<CustomerCountry, Integer> getCustomersInCountry() {
        return null;
    }

    /**
     * Helper Method
     * @param SQLQuery
     * @param params
     * @return
     */
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
}