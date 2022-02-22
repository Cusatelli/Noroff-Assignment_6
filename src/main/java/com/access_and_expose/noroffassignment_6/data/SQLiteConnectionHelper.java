package com.access_and_expose.noroffassignment_6.data;

public class SQLiteConnectionHelper {
    public static String getConnectionString() {
        return "jdbc:sqlite::resource:Chinook.sqlite";
    }
}
