package com.access_and_expose.noroffassignment_6.data.repository.genre;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.model.Genre;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class GenreRepository implements IGenreRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    public GenreRepository(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Collection<Genre> getAll() {
        String SQLQuery = "SELECT * FROM Genre";
        return getFromSQLDatabase(SQLQuery);
    }

    @Override
    public Collection<Genre> getAll(int offset, int limit) {
        return null;
    }

    @Override
    public Genre getById(String genreId) {
        String SQLQuery = "SELECT * FROM Genre WHERE GenreId LIKE ?";
        return getFromSQLDatabase(SQLQuery, genreId).get(0);
    }

    @Override
    public Collection<Genre> getByName(String genreName) {
        String SQLQuery = "SELECT * FROM Genre WHERE Name LIKE ?";
        return getFromSQLDatabase(SQLQuery, genreName);
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
