package com.joysistvi.recording.repository;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRepositoryImpl implements SongRepository{

    private final DbConnection dbConnection; // Composition

    // Constructor injection
    public SongRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // list of song
    @Override
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT s.id, s.title, s.length, s.genre, a.name " +
                "FROM songs s " +
                "JOIN albums a ON s.album_id = a.id " +
                "WHERE s.is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {


            while (res.next()) {
                songs.add(new Song(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("length"),
                        res.getString("genre"),
                        res.getString("name")
                ));

            }

        } catch (SQLException e) {
            System.out.println("Read Songs With Album: " + e.getMessage());
        }

        return songs;
    }

    // Create Song
    @Override
    public boolean createSong(Song song) {
        String query = "INSERT INTO songs (title, length, genre, album_id) " + // create statement
                "VALUES (?,?,?,?)"; // Anti-SQL Injection

        // Try-with-resources: automatically close opened connection
        try (Connection connection = dbConnection.connect();
             PreparedStatement prep = connection.prepareStatement(query)) {
            // Bind values to the placeholders in the query
            prep.setString(1, song.getTitle());
            prep.setString(2, song.getLength());
            prep.setString(3, song.getGenre());
            prep.setInt(4, song.getAlbumId());

            // Execute the insert statement
            int rowsAffected = prep.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            // Print the error message if something goes wrong
            System.out.println("Error in inserting song: " + e.getMessage());

        }
        return false;
    }

    // Update Song
    @Override
    public boolean updateSong(String title, String length, String genre, int id) {
        String query = "UPDATE songs SET title = ?, length = ?, genre = ? WHERE id = ?"; // parameterized query

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            // setting parameter wild cards
            prep.setString(1, title);
            prep.setString(2, length);
            prep.setString(3, genre);
            prep.setInt(4, id);

            int rowsAffected = prep.executeUpdate();
            return rowsAffected > 0;


        } catch (SQLException e) {
            System.out.println("Update Song: " + e.getMessage());

        }
        return false;
    }

    // Hard Delete Song
    @Override
    public boolean deleteSong(int id) {
        String query = "DELETE FROM songs WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Delete Song: " + e.getMessage());
        }
        return false;
    }

    // Soft Delete Song
    @Override
    public boolean archiveSong(int id) {

        String query = "UPDATE songs SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Archive Song: " + e.getMessage());
        }

        return false;
    }

    // Restore a Song
    @Override
    public boolean restoreSong(int id) {

        String query = "UPDATE songs SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Restore Song: " + e.getMessage());
        }

        return false;
    }

    // to see all in the archive
    @Override
    public List<Song> getArchivedSongs() {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT s.id, s.title, s.length, s.genre, a.name " +
                        "FROM songs s " +
                        "JOIN albums a ON s.album_id = a.id " +
                        "WHERE s.is_archived = 1";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {
                songs.add(new Song(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("length"),
                        res.getString("genre"),
                        res.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("View Archived Songs: " + e.getMessage());
        }

        return songs;
    }

    // to search the either a keyword only
    @Override
    public List<Song> searchSongs(String keyword) {

        List<Song> songs = new ArrayList<>();

        String query =
                "SELECT s.id, s.title, s.length, s.genre, a.name " +
                        "FROM songs s " +
                        "JOIN albums a ON s.album_id = a.id " +
                        "WHERE LOWER(s.title) LIKE LOWER(?) " +
                        "AND s.is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            ResultSet res = prep.executeQuery();

            while (res.next()) {

                songs.add(new Song(
                        res.getInt("id"),
                        res.getString("title"),
                        res.getString("length"),
                        res.getString("genre"),
                        res.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Search Song: " + e.getMessage());
        }

        return songs;
    }


}
