package com.joysistvi.recording.repository;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.model.Album;
import com.joysistvi.recording.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepository {

    private final DbConnection dbConnection;

    public AlbumRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Album> getAllAlbums() {

        List<Album> albums = new ArrayList<>();

        String query =
                "SELECT al.id, al.name, al.year, ar.name AS artist_name " +
                        "FROM albums al " +
                        "JOIN artists ar ON al.artist_id = ar.id " +
                        "WHERE al.is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {

                albums.add(new Album(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("year"),
                        res.getString("artist_name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Read Albums: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public boolean createAlbum(Album album) {

        String query = "INSERT INTO albums (name, year, artist_id) VALUES (?,?,?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, album.getName());
            prep.setInt(2, album.getYear());
            prep.setInt(3, album.getArtistId());

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Create Album: " + e.getMessage());
        }

        return false;
    }

@Override
public boolean updateAlbum(String name, int year, int artistId, int id) {

    String query =
            "UPDATE albums SET name = ?, year = ?, artist_id = ? WHERE id = ?";

    try (Connection conn = dbConnection.connect();
         PreparedStatement prep = conn.prepareStatement(query)) {

        prep.setString(1, name);
        prep.setInt(2, year);
        prep.setInt(3, artistId);
        prep.setInt(4, id);

        int rowsAffected = prep.executeUpdate();

        return rowsAffected > 0;

    } catch (SQLException e) {
        System.out.println("Update Album: " + e.getMessage());
    }

    return false;
}

    @Override
    public boolean deleteAlbum(int id) {

        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Delete Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveAlbum(int id) {

        String query = "UPDATE albums SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Archive Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreAlbum(int id) {

        String query = "UPDATE albums SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Restore Album: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<Album> getArchivedAlbums() {
        List<Album> albums = new ArrayList<>();

        String query = "SELECT al.id, al.name, al.year, ar.name AS artist_name " +
                "FROM albums al " +
                "JOIN artists ar ON al.artist_id = ar.id " +
                "WHERE al.is_archived = 1";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {
                albums.add(new Album(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("year"),
                        res.getString("artist_name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("View Archived Albums: " + e.getMessage());
        }

        return albums;
    }

    @Override
    public List<Album> searchAlbums(String keyword) {
        List<Album> albums = new ArrayList<>();

        String query =
                "SELECT al.id, al.name, al.year, ar.name AS artist_name " +
                        "FROM albums al " +
                        "JOIN artists ar ON al.artist_id = ar.id " +
                        "WHERE LOWER(al.name) LIKE LOWER(?) " +
                        "AND al.is_archived = 0";
        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            ResultSet res = prep.executeQuery();

            while (res.next()) {

                albums.add(new Album(
                        res.getInt("id"),
                        res.getString("name"),
                        res.getInt("year"),
                        res.getString("artist_name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Search Album: " + e.getMessage());
        }

        return albums;
}

    @Override
    public boolean albumExists(String name, int artistId) {

        String sql = "SELECT COUNT(*) FROM albums WHERE name = ? AND artist_id = ? AND is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, artistId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}


