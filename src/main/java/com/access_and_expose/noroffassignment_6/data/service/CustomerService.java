package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.CustomerRepository;
import com.access_and_expose.noroffassignment_6.model.Genre;
import com.access_and_expose.noroffassignment_6.model.Order;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.model.customer.CustomerCountry;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

@Service
public class CustomerService implements CustomerRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    /**
     * Customer Service Constructor
     * @param databaseConnectionFactory database connection URL.
     */
    public CustomerService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    /**
     * Get All Customer from Database using "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer" Query.
     * @return Collection of Customer Model Objects.
     */
    @Override
    public Collection<Customer> getAll() {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer";
        return getFromSQLDatabase(SQLQuery);
    }

    /**
     * Get All Customer from Database using "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId BETWEEN ? AND ?" Query.
     * @param offset start getting all elements from offset value - default to 0
     * @param limit end getting all elements at offset + limit.
     * @return Collection of Customer model objects.
     */
    @Override
    public Collection<Customer> getAll(@PathVariable int offset, @PathVariable int limit) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId BETWEEN ? AND ?";
        return getFromSQLDatabase(SQLQuery, String.valueOf(offset), String.valueOf(offset + limit));
    }

    /**
     * Get Customer by id using SQL Queries from SQL Database Chinook.
     * @param customerId id to search for in Database.
     * @return Customer model object.
     */
    @Override
    public Customer getById(@PathVariable String customerId) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE CustomerId = ?";
        return getFromSQLDatabase(SQLQuery, customerId).get(0);
    }

    /**
     * Get Customer by name using SQL Queries from SQL Database Chinook.
     * @param firstName name to search for in Database.
     * @return Customer model object.
     */
    @Override
    public Collection<Customer> getByName(@PathVariable String firstName) {
        String SQLQuery = "SELECT CustomerId, FirstName, LastName, Country, PostalCode, Phone, Email FROM Customer WHERE FirstName LIKE ?";
        return getFromSQLDatabase(SQLQuery, firstName);
    }

    /**
     * Add new Customer to SQL Database Chinook using SQL Queries.
     * @param customer Customer model Object.
     * @return the Customer Model Object inserted.
     */
    @Override
    public Customer add(@PathVariable Customer customer) {
        String SQLQuery = "INSERT INTO Customer(FirstName, LastName, Country, PostalCode, Phone, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = databaseConnectionFactory.getConnection()) {
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

    /**
     * Update existing Customer to SQL Database Chinook using SQL Queries.
     * @param item Customer model Object.
     * @return true if update successful.
     */
    @Override
    public boolean update(Customer item) {
        String SQLQuery = "UPDATE Customer SET FirstName = ?, LastName = ?, Phone = ?, Email = ?, Country = ?, PostalCode = ? WHERE CustomerId = ?";

        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.setInt(3, item.getPhone());
            preparedStatement.setString(4, item.getEmail());
            preparedStatement.setString(5, item.getCustomerCountry().getCountryName());
            preparedStatement.setString(6, item.getCustomerCountry().getPostalCode());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Delete Customer in SQL Database Chinook using SQL Queries.
     * @param id id to delete.
     * @return true if delete successful.
     */
    @Override
    public boolean deleteById(String id) {
        String SQLQuery = "DELETE FROM Customer WHERE CustomerID = ?";

        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            preparedStatement.setString(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    /**
     * Get Customer & Sort by Country from SQL Database using SQL Queries.
     * @param order Ascending or Descending order.
     * @return LinkedHashMap<String, Integer> ordered by insertion.
     */
    @Override
    public LinkedHashMap<Customer, Integer> getSpender(Order order) {
        String ORDER = "";
        if(order.equals(Order.ASCENDING)) { ORDER = "ASC"; }
        if(order.equals(Order.DESCENDING)) { ORDER = "DESC"; }

        String SQLQuery = "SELECT Customer.CustomerId, FirstName, LastName, SUM(Total) FROM Customer " +
                "JOIN Invoice ON Customer.CustomerId = Invoice.CustomerId " +
                "GROUP BY Invoice.CustomerId ORDER BY SUM(Total) " + ORDER;

        LinkedHashMap<Customer, Integer> customers = new LinkedHashMap<>();
        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getLong("CustomerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName")
                );
                customers.put(customer, resultSet.getInt("SUM(TOTAL)"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    /**
     * Get Customer Favorite Genre base on their invoice history from SQL Database using SQL Queries.
     * @param customerId Customer id.
     * @param order Ascending or Descending order.
     * @return LinkedHashMap<Genre, Integer> ordered by insertion.
     */
    @Override
    public LinkedHashMap<Genre, Integer> getFavoriteGenre(String customerId, Order order) {
        String ORDER = "";
        if(order.equals(Order.ASCENDING)) { ORDER = "ASC"; }
        if(order.equals(Order.DESCENDING)) { ORDER = "DESC"; }

        String SQLQuery = "SELECT InvoiceLine.InvoiceId, InvoiceLine.TrackId, Invoice.CustomerId, Track.GenreId, Genre.Name, COUNT(Genre.GenreId) " +
                "FROM InvoiceLine " +
                "INNER JOIN Invoice ON InvoiceLine.InvoiceId = Invoice.InvoiceId " +
                "INNER JOIN Track ON InvoiceLine.TrackId = Track.TrackId " +
                "INNER JOIN Customer ON Invoice.CustomerId = Customer.CustomerId " +
                "INNER JOIN Genre ON Track.GenreId = Genre.GenreId " +
                "WHERE Customer.CustomerId = ? " +
                "GROUP BY Genre.GenreId " +
                "ORDER BY COUNT(Track.GenreId) " + ORDER + " LIMIT 2";

                LinkedHashMap<Genre, Integer> favorites = new LinkedHashMap<>();
        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            preparedStatement.setString(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getLong("GenreId"),
                        resultSet.getString("Name")
                );

                favorites.put(genre, resultSet.getInt("COUNT(Genre.GenreId)"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return favorites;
    }

    /**
     * Get Customer & Sort by Country from SQL Database using SQL Queries.
     * @param order Ascending or Descending order.
     * @return LinkedHashMap<String, Integer> ordered by insertion.
     */
    @Override
    public LinkedHashMap<String, Integer> sortByCountry(Order order) {
        String ORDER = "";
        if(order.equals(Order.ASCENDING)) { ORDER = "ASC"; }
        if(order.equals(Order.DESCENDING)) { ORDER = "DESC"; }

        String SQLQuery = "SELECT Country, COUNT(CustomerId) FROM Customer " +
                "GROUP BY Country " +
                "ORDER BY COUNT(CustomerId) " + ORDER;

        LinkedHashMap<String, Integer> countryCount = new LinkedHashMap<>();
        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String customerCountry = resultSet.getString("Country");
                String customerCountryCount = resultSet.getString("COUNT(CustomerId)");
                System.out.println("Country: " + customerCountry + " : " + customerCountryCount);
                countryCount.put(customerCountry, Integer.parseInt(customerCountryCount));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countryCount;
    }

    /**
     * Helper method to Get Data from SQL Database.
     * @param SQLQuery search query.
     * @param params preparedStatement input.
     * @return ArrayList of Model Objects.
     */
    @Override
    public ArrayList<Customer> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = databaseConnectionFactory.getConnection()) {
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