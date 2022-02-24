package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.AlbumRepository;
import com.access_and_expose.noroffassignment_6.model.Album;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class AlbumService implements AlbumRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    public AlbumService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Collection<Album> getAll() {
        String SQLQuery = "SELECT * FROM Album";
        return getFromSQLDatabase(SQLQuery);
    }

    @Override
    public Collection<Album> getAll(int offset, int limit) {
        return null;
    }

    @Override
    public Album getById(String albumId) {
        String SQLQuery = "SELECT * FROM Album WHERE AlbumId = ?";
        return getFromSQLDatabase(SQLQuery, albumId).get(0);
    }

    @Override
    public Collection<Album> getByName(String albumName) {
        String SQLQuery = "SELECT * FROM Album WHERE Title LIKE ?";
        return getFromSQLDatabase(SQLQuery, albumName);
    }

    @Override
    public Album add(Album item) {
        return null;
    }

    @Override
    public boolean update(Album item) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public ArrayList<Album> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Album> albums = new ArrayList<>();
        try (Connection connection = this.databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for(int i = 1; i < params.length + 1; i++) {
                preparedStatement.setString(i, params[i -1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Album album = new Album(
                        resultSet.getLong("AlbumId"),
                        resultSet.getLong("ArtistId"),
                        resultSet.getString("Title")
                );

                albums.add(album);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return albums;
    }
}
