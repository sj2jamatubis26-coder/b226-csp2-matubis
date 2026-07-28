package com.joysistvi.recording.repository;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.model.Artist;
import com.joysistvi.recording.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository{

    private final DbConnection dbConnection; // Composition

    // Constructor injection
    public ArtistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // list of Artist
    @Override
    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT id, name " +
                "FROM artists " +
                "WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {


            while (res.next()) {
                artists.add(new Artist(
                        res.getInt("id"),
                        res.getString("name")
                ));

            }

        } catch (SQLException e) {
            System.out.println("Read Artists: " + e.getMessage());
        }

        return artists;
    }


    // Create Artist
    @Override
    public boolean createArtist(Artist artist) {

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement prep = connection.prepareStatement(query)) {

            prep.setString(1, artist.getName());

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Create Artist: " + e.getMessage());

        }

        return false;
    }

    // Update Artist
    @Override
    public boolean updateArtist(String name, int id) {

        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement prep = connection.prepareStatement(query)) {

            prep.setString(1, name);
            prep.setInt(2, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {

            System.out.println("Update Artist: " + e.getMessage());

        }

        return false;
    }

    // Hard Delete Artist
    @Override
    public boolean deleteArtist(int id) {
        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Delete Artist: " + e.getMessage());
        }
        return false;
    }

    // Soft Delete Artist
    @Override
    public boolean archiveArtist(int id) {

        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Archive Artist: " + e.getMessage());
        }

        return false;
    }
    // Restore a Artist
    @Override
    public boolean restoreArtist(int id) {

        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Restore Artist: " + e.getMessage());
        }

        return false;
    }


    // to see all in the archive

    @Override
    public List<Artist> getArchivedArtists() {

        List<Artist> artists = new ArrayList<>();

        String query = "SELECT id, name FROM artists WHERE is_archived = 1";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {
                artists.add(new Artist(
                        res.getInt("id"),
                        res.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("View Archived Artists: " + e.getMessage());
        }

        return artists;
    }



    // to search the either a keyword only
    @Override
    public List<Artist> searchArtists(String keyword) {

        List<Artist> artists = new ArrayList<>();

        String query =
                "SELECT id, name " +
                        "FROM artists " +
                        "WHERE LOWER(name) LIKE LOWER(?) " +
                        "AND is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            ResultSet res = prep.executeQuery();

            while (res.next()) {

                artists.add(new Artist(
                        res.getInt("id"),
                        res.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Search Artist: " + e.getMessage());
        }

        return artists;
    }


}
