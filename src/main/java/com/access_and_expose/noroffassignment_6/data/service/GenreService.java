package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.GenreRepository;
import com.access_and_expose.noroffassignment_6.model.Genre;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class GenreService implements GenreRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    /**
     * Genre Service Constructor
     * @param databaseConnectionFactory database connection URL.
     */
    public GenreService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    /**
     * Get All Genre from Database using "SELECT * FROM Genre" Query.
     * @return Collection of Genre Model Objects.
     */
    @Override
    public Collection<Genre> getAll() {
        String SQLQuery = "SELECT * FROM Genre";
        return getFromSQLDatabase(SQLQuery);
    }

    /**
     * Get Genre by id using SQL Queries from SQL Database Chinook.
     * @param genreId id to search for in Database.
     * @return Genre model object.
     */
    @Override
    public Genre getById(String genreId) {
        String SQLQuery = "SELECT * FROM Genre WHERE GenreId LIKE ?";
        return getFromSQLDatabase(SQLQuery, genreId).get(0);
    }

    /**
     * Get Genre by name using SQL Queries from SQL Database Chinook.
     * @param genreName name to search for in Database.
     * @return Genre model object.
     */
    @Override
    public Collection<Genre> getByName(String genreName) {
        String SQLQuery = "SELECT * FROM Genre WHERE Name LIKE ?";
        return getFromSQLDatabase(SQLQuery, genreName);
    }

    @Override
    public Collection<Genre> getAll(int offset, int limit) {
        return null;
    }

    @Override
    public Genre add(Genre item) {
        return null;
    }

    @Override
    public boolean update(Genre item) {
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
    public ArrayList<Genre> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Genre> genres = new ArrayList<>();

        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getLong("GenreId"),
                        resultSet.getString("Name")
                );

                genres.add(genre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return genres;
    }
}
