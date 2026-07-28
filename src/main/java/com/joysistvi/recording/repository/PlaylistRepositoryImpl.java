package com.joysistvi.recording.repository;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.model.Playlist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistRepositoryImpl implements PlaylistRepository {

    private final DbConnection dbConnection;

    public PlaylistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    @Override
    public boolean createPlaylist(Playlist playlist) {

        String query = "INSERT INTO playlists (user_id, name) VALUES (?, ?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, playlist.getUserId());
            ps.setString(2, playlist.getName());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Playlist> getAllPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM playlists WHERE is_archived = 0";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                Playlist playlist = new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name")
                );

                playlists.add(playlist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }


    @Override
    public List<Playlist> getPlaylistsByUserId(int userId) {

        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM playlists WHERE user_id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Playlist playlist = new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name")
                );

                playlists.add(playlist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }


    @Override
    public boolean updatePlaylist(Playlist playlist) {

        String query = "UPDATE playlists SET name = ? WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, playlist.getName());
            ps.setInt(2, playlist.getId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deletePlaylist(int id) {

        String query = "DELETE FROM playlists WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Playlist searchPlaylistById(int id) {

        String query = "SELECT * FROM playlists WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Playlist(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Playlist> searchPlaylistByName(String name) {

        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM playlists WHERE name LIKE ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                playlists.add(new Playlist(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public boolean archivePlaylist(int id) {

        String query = "UPDATE playlists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Archive Playlist: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Playlist> getArchivedPlaylists() {

        List<Playlist> playlists = new ArrayList<>();

        String query = "SELECT * FROM playlists WHERE is_archived = 1";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                Playlist playlist = new Playlist(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name")
                );

                playlists.add(playlist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public boolean restorePlaylist(int id) {

        String query = "UPDATE playlists SET is_archived = 0 WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}