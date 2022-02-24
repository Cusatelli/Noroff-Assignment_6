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

    /**
     * Album Service Constructor
     * @param databaseConnectionFactory database connection URL.
     */
    public AlbumService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    /**
     * Get All Albums from Database using "SELECT * FROM Album" Query.
     * @return Collection of Album Model Objects.
     */
    @Override
    public Collection<Album> getAll() {
        String SQLQuery = "SELECT * FROM Album";
        return getFromSQLDatabase(SQLQuery);
    }

    /**
     * Get Album by id using SQL Queries from SQL Database Chinook.
     * @param albumId id to search for in Database.
     * @return Album model object.
     */
    @Override
    public Album getById(String albumId) {
        String SQLQuery = "SELECT * FROM Album WHERE AlbumId = ?";
        return getFromSQLDatabase(SQLQuery, albumId).get(0);
    }

    /**
     * Get Album by name using SQL Queries from SQL Database Chinook.
     * @param albumName name to search for in Database.
     * @return Album model object.
     */
    @Override
    public Collection<Album> getByName(String albumName) {
        String SQLQuery = "SELECT * FROM Album WHERE Title LIKE ?";
        return getFromSQLDatabase(SQLQuery, albumName);
    }

    @Override
    public Collection<Album> getAll(int offset, int limit) {
        return null;
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

    /**
     * Helper method to Get Data from SQL Database.
     * @param SQLQuery search query.
     * @param params preparedStatement input.
     * @return ArrayList of Model Objects.
     */
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
