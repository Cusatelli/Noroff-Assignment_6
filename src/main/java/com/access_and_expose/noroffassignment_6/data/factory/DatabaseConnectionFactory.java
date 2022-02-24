package com.access_and_expose.noroffassignment_6.data.factory;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnectionFactory {
    Connection getConnection() throws SQLException;
}
