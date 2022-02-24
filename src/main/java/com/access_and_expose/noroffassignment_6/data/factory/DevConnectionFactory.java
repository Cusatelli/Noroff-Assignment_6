package com.access_and_expose.noroffassignment_6.data.factory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Profile("!production")
public class DevConnectionFactory implements DatabaseConnectionFactory {

    private static final String URL = "jdbc:sqlite:src/main/resources/Chinook.sqlite";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
