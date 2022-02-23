package com.access_and_expose.noroffassignment_6.data.track;

import com.access_and_expose.noroffassignment_6.data.SQLiteConnectionHelper;
import com.access_and_expose.noroffassignment_6.model.track.Track;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class TrackRepository implements ITrackRepository {

    @Override
    public Track getById(String trackId) {
        String SQLQuery = "SELECT TrackId, Name, AlbumId, GenreId, UnitPrice FROM Track WHERE TrackId LIKE ?";
        return getFromSQLDatabase(SQLQuery, trackId);
    }

    @Override
    public Track getByName(String trackName) {
        String SQLQuery = "SELECT TrackId, Name, AlbumId, GenreId, UnitPrice FROM Track WHERE Name LIKE ?";
        return getFromSQLDatabase(SQLQuery, trackName);
    }

    private Track getFromSQLDatabase(String SQLQuery, String... params) {
        Track track = null;
        try (Connection connection = DriverManager.getConnection(SQLiteConnectionHelper.getConnectionString())) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery);
            for (int index = 1; index < params.length + 1; index++) {
                preparedStatement.setString(index, params[index - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                track = new Track(
                        resultSet.getLong("TrackId"),
                        resultSet.getString("Name"),
                        resultSet.getLong("AlbumId"),
                        resultSet.getLong("GenreId"),
                        resultSet.getLong("UnitPrice")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return track;
    }
}
