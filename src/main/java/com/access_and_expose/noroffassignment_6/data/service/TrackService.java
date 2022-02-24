package com.access_and_expose.noroffassignment_6.data.service;

import com.access_and_expose.noroffassignment_6.data.factory.DatabaseConnectionFactory;
import com.access_and_expose.noroffassignment_6.data.repository.TrackRepository;
import com.access_and_expose.noroffassignment_6.model.Track;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class TrackService implements TrackRepository {

    private final DatabaseConnectionFactory databaseConnectionFactory;

    public TrackService(DatabaseConnectionFactory databaseConnectionFactory) {
        this.databaseConnectionFactory = databaseConnectionFactory;
    }

    @Override
    public Collection<Track> getAll() {
        String SQLQuery = "SELECT * FROM Track";
        return getFromSQLDatabase(SQLQuery);
    }

    @Override
    public Collection<Track> getAll(@PathVariable int offset, @PathVariable int limit) {
        String SQLQuery = "SELECT * FROM Track WHERE TrackId BETWEEN ? AND ?";
        return getFromSQLDatabase(SQLQuery, String.valueOf(offset), String.valueOf(offset + limit));
    }

    @Override
    public Track getById(String trackId) {
        String SQLQuery = "SELECT TrackId, Name, AlbumId, GenreId, UnitPrice FROM Track WHERE TrackId LIKE ?";
        return getFromSQLDatabase(SQLQuery, trackId).get(0);
    }

    @Override
    public Collection<Track> getByName(String trackName) {
        String SQLQuery = "SELECT TrackId, Name, AlbumId, GenreId, UnitPrice FROM Track WHERE Name LIKE ?";
        return getFromSQLDatabase(SQLQuery, trackName);
    }

    @Override
    public ArrayList<Track> getByKeyword(String keyword) {
        String SQLQuery = "SELECT TrackId, Name, AlbumId, GenreId, UnitPrice FROM Track WHERE Name LIKE ?";
        return getFromSQLDatabase(SQLQuery, keyword);
    }

    @Override
    public Track add(Track item) {
        return null;
    }

    @Override
    public boolean update(Track item) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public ArrayList<Track> getFromSQLDatabase(String SQLQuery, String... params) {
        ArrayList<Track> tracks = new ArrayList<>();

        try (Connection connection = this.databaseConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Track track = new Track(
                        resultSet.getLong("TrackId"),
                        resultSet.getString("Name"),
                        resultSet.getLong("AlbumId"),
                        resultSet.getLong("GenreId"),
                        resultSet.getLong("UnitPrice")
                );

                tracks.add(track);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tracks;
    }
}
