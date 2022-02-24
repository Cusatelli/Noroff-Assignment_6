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

    public InvoiceService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Collection<Invoice> getAll() {
        String SQLQuery = "SELECT * FROM Invoice";
        return getFromSQLDatabase(SQLQuery);
    }

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
