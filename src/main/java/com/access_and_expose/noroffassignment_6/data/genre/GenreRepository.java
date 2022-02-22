package com.access_and_expose.noroffassignment_6.data.genre;

import com.access_and_expose.noroffassignment_6.data.SQLiteConnectionHelper;
import com.access_and_expose.noroffassignment_6.model.genre.Genre;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class GenreRepository implements IGenreRepository {

    @Override
    public Genre getGenreById(String genreId) {
        String SQLQuery = "SELECT * FROM Genre WHERE GenreId LIKE ?";
        return getGenre(SQLQuery, genreId);
    }

    @Override
    public Genre getGenreByName(String genreName) {
        String SQLQuery = "SELECT * FROM Genre WHERE Name LIKE ?";
        return getGenre(SQLQuery, genreName);
    }

    private Genre getGenre(String SQLQuery, String... params) {
        Genre genre = null;
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                genre = new Genre(
                        resultSet.getLong("GenreId"),
                        resultSet.getString("Name")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return genre;
    }
}
