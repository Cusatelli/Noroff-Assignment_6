package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.InvoiceRepository;
import com.access_and_expose.noroffassignment_6.model.Invoice;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class InvoiceService implements InvoiceRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    /**
     * Invoice Service Constructor
     * @param databaseConnectionFactory database connection URL.
     */
    public InvoiceService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    /**
     * Get All Invoice from Database using "SELECT * FROM Invoice" Query.
     * @return Collection of Invoice Model Objects.
     */
    @Override
    public Collection<Invoice> getAll() {
        String SQLQuery = "SELECT * FROM Invoice";
        return getFromSQLDatabase(SQLQuery);
    }

    /**
     * Get All Invoice from Database using "SELECT * FROM Invoice WHERE InvoiceId BETWEEN ? AND ?" Query.
     * @param offset start getting all elements from offset value - default to 0
     * @param limit end getting all elements at offset + limit.
     * @return Collection of Invoice model objects.
     */
    @Override
    public Collection<Invoice> getAll(int offset, int limit) {
        String SQLQuery = "SELECT * FROM Invoice WHERE InvoiceId BETWEEN ? AND ?";
        return getFromSQLDatabase(SQLQuery, String.valueOf(offset), String.valueOf(offset + limit));
    }

    @Override
    public Invoice getById(String id) {
        return null;
    }

    @Override
    public Collection<Invoice> getByName(String name) {
        return null;
    }

    @Override
    public Invoice add(Invoice item) {
        return null;
    }

    @Override
    public boolean update(Invoice item) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    /**
     * Helper method to Get Data from SQL Database.
     * @param SQLQuery search query.
     * @param params preparedStatement input.
     * @return ArrayList of Model Objects.
     */
    @Override
    public ArrayList<Invoice> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Invoice> invoiceLines = new ArrayList<>();

        try (Connection connection = this.databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoiceLine = new Invoice(
                        resultSet.getLong("InvoiceId"),
                        resultSet.getLong("CustomerId"),
                        resultSet.getLong("Total")
                );

                invoiceLines.add(invoiceLine);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return invoiceLines;
    }
}
