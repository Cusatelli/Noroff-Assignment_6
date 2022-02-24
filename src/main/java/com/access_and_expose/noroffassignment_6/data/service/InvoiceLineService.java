package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.InvoiceLineRepository;
import com.access_and_expose.noroffassignment_6.model.InvoiceLine;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class InvoiceLineService implements InvoiceLineRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    public InvoiceLineService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Collection<InvoiceLine> getAll() {
        String SQLQuery = "SELECT * FROM InvoiceLine";
        return getFromSQLDatabase(SQLQuery);
    }

    @Override
    public Collection<InvoiceLine> getAll(int offset, int limit) {
        String SQLQuery = "SELECT * FROM InvoiceLine WHERE InvoiceLineId BETWEEN ? AND ?";
        return getFromSQLDatabase(SQLQuery, String.valueOf(offset), String.valueOf(offset + limit));
    }

    @Override
    public InvoiceLine getById(String id) {
        String SQLQuery = "SELECT * FROM InvoiceLine WHERE InvoiceLineId = ?";
        return getFromSQLDatabase(SQLQuery, id).get(0);
    }

    @Override
    public Collection<InvoiceLine> getByName(String name) {
        return null;
    }

    @Override
    public InvoiceLine add(InvoiceLine item) {
        return null;
    }

    @Override
    public boolean update(InvoiceLine item) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public ArrayList<InvoiceLine> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<InvoiceLine> invoices = new ArrayList<>();
        try (Connection connection = this.databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InvoiceLine invoiceLine = new InvoiceLine(
                        resultSet.getLong("InvoiceLineId"),
                        resultSet.getLong("InvoiceId"),
                        resultSet.getLong("TrackId"),
                        resultSet.getLong("UnitPrice"),
                        resultSet.getLong("Quantity")
                );

                invoices.add(invoiceLine);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return invoices;
    }
}
