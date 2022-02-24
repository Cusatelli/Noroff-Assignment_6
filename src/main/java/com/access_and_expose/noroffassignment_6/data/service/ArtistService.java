package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.ArtistRepository;
import com.access_and_expose.noroffassignment_6.model.Artist;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class ArtistService implements ArtistRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    /**
     * Artist Service Constructor
     * @param databaseConnectionFactory database connection URL.
     */
    public ArtistService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    /**
     * Get All Artist from Database using "SELECT * FROM Artist" Query.
     * @return Collection of Artist Model Objects.
     */
    @Override
    public Collection<Artist> getAll() {
        String SQLQuery = "SELECT * FROM Artist";
        return getFromSQLDatabase(SQLQuery);
    }

    /**
     * Get Artist by id using SQL Queries from SQL Database Chinook.
     * @param artistId id to search for in Database.
     * @return Artist model object.
     */
    @Override
    public Artist getById(String artistId) {
        String SQLQuery = "SELECT * FROM Artist WHERE ArtistId = ?";
        return getFromSQLDatabase(SQLQuery, artistId).get(0);
    }

    /**
     * Get Artist by name using SQL Queries from SQL Database Chinook.
     * @param artistName name to search for in Database.
     * @return Artist model object.
     */
    @Override
    public Collection<Artist> getByName(String artistName) {
        String SQLQuery = "SELECT * FROM Artist WHERE Name LIKE ?";
        return getFromSQLDatabase(SQLQuery, artistName);
    }

    @Override
    public Collection<Artist> getAll(int offset, int limit) {
        return null;
    }

    @Override
    public Artist add(Artist item) {
        return null;
    }

    @Override
    public boolean update(Artist item) {
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
    public ArrayList<Artist> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Artist> artists = new ArrayList<>();

        try (Connection connection = databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int i = 1; i < params.length + 1; i++) {
                preparedStatement.setString(i, params[i - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Artist artist = new Artist(
                        resultSet.getLong("ArtistId"),
                        resultSet.getString("Name")
                );

                artists.add(artist);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }
}