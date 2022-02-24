package com.access_and_expose.noroffassignment_6.data.factory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Profile("production")
public class ProdConnectionFactory implements DatabaseConnectionFactory {

    static final String URL = "jdbc:sqlite::resource:Chinook.sqlite";

    /**
     * Get the Connection String URL used to connect to Database.
     * @return database URL.
     * @throws SQLException error.
     */
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
