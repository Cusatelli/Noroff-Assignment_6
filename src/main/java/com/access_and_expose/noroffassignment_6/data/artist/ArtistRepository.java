package com.access_and_expose.noroffassignment_6.data.artist;
import com.access_and_expose.noroffassignment_6.data.SQLiteConnectionHelper;
import com.access_and_expose.noroffassignment_6.model.artist.Artist;

import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class ArtistRepository implements IArtistRepository {

    @Override
    public Artist getById(String artistId) {
        String SQLQuery = "SELECT * FROM Artist WHERE ArtistId = ?";
        return getArtist(SQLQuery, artistId);
    }

    @Override
    public Artist getByName(String artistName) {
        String SQLQuery = "SELECT * FROM Artist WHERE Name LIKE ?";
        return getArtist(SQLQuery, artistName);
    }

    private Artist getArtist(String SQLQuery, String... params) {
        Artist artists = null;
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int i = 1; i < params.length + 1; i++) {
                preparedStatement.setString(i, params[i - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                artists = new Artist(
                        resultSet.getLong("ArtistId"),
                        resultSet.getString("Name")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }
}