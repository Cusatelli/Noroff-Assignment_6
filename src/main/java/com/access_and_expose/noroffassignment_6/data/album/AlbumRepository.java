package com.access_and_expose.noroffassignment_6.data.album;

import com.access_and_expose.noroffassignment_6.data.SQLiteConnectionHelper;
import com.access_and_expose.noroffassignment_6.model.album.Album;

import org.springframework.stereotype.Service;
import java.sql.*;

@Service
public class AlbumRepository implements IAlbumRepository {

    @Override
    public Album getById(String albumId) {
        String SQLQuery = "SELECT AlbumId, Title FROM Album WHERE AlbumId = ?";
        return getAlbums(SQLQuery, albumId);
    }

    @Override
    public Album getByName(String albumName) {
        String SQLQuery = "SELECT * FROM Album WHERE Title LIKE ?";
        return getAlbums(SQLQuery, albumName);
    }

    private Album getAlbums(String SQLQuery, String... params) {
        Album albums = null;
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for(int i = 1; i < params.length + 1; i++) {
                preparedStatement.setString(i, params[i -1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                albums = new Album(
                        resultSet.getLong("AlbumId"),
                        resultSet.getString("Title")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return albums;
    }
}