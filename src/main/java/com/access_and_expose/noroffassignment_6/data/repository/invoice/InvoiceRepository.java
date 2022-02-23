package com.access_and_expose.noroffassignment_6.data.repository.invoice;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.model.Invoice;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

@Service
public class InvoiceRepository implements IInvoiceRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    public InvoiceRepository(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Collection<Invoice> getAll() {
        String SQLQuery = "SELECT * FROM Invoice";
        return getFromSQLDatabase(SQLQuery);
    }

    @Override
    public Collection<Invoice> getAll(int offset, int limit) {
        return null;
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
    public LinkedHashMap<Long, Long> getByHighestSpender() {
        String SQLQuery = "SELECT CustomerId, SUM(Total) FROM Invoice " +
                "GROUP BY CustomerId " +
                "ORDER BY SUM(Total) DESC"; // TODO: Make sure list is sorted...

        LinkedHashMap<Long, Long> invoiceTotal = new LinkedHashMap<>();
        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long customerId = resultSet.getLong("CustomerId");
                int customerTotalSpent = resultSet.getInt("SUM(Total)");

                // TODO: Check total sum. (0 is returned as 6 & result is 6 too high!).
                //  Otherwise Query works fine.
                invoiceTotal.put(customerId, (long) customerTotalSpent);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return invoiceTotal;
    }

    @Override
    public ArrayList<Invoice> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Invoice> invoices = new ArrayList<>();
        try (Connection connection = this.databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice(
                        resultSet.getLong("InvoiceId"),
                        resultSet.getLong("CustomerId"),
                        resultSet.getLong("Total")
                );

                invoices.add(invoice);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return invoices;
    }
}
