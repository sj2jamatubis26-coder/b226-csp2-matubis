package com.joysistvi.recording.repository;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final DbConnection dbConnection;

    public UserRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query =
                "SELECT id, first_name, last_name, username, email, role " +
                        "FROM users " +
                        "WHERE is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {

                users.add(new User(
                        res.getInt("id"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("username"),
                        res.getString("email"),
                        res.getString("role")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Read Users: " + e.getMessage());
        }

        return users;
    }

    @Override
    public boolean createUser(User user) {

        String query = "INSERT INTO users (first_name, last_name, username, email, password, role) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, user.getFirstName());
            prep.setString(2, user.getLastName());
            prep.setString(3, user.getUsername());
            prep.setString(4, user.getEmail());
            prep.setString(5, user.getPassword());
            prep.setString(6, user.getRole());

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Create User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateUser(String firstName, String lastName,
                              String username, String email,
                              String password, String role, int id) {

        String query =
                "UPDATE users " +
                        "SET first_name = ?, last_name = ?, username = ?, " +
                        "email = ?, password = ?, role = ? " +
                        "WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.setString(3, username);
            prep.setString(4, email);
            prep.setString(5, password);
            prep.setString(6, role);
            prep.setInt(7, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Update User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        String query = "DELETE FROM users WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Delete User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean archiveUser(int id) {

        String query = "UPDATE users SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Archive User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean restoreUser(int id) {

        String query = "UPDATE users SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Restore User: " + e.getMessage());
        }

        return false;
    }

    @Override
    public List<User> getArchivedUsers() {

        List<User> users = new ArrayList<>();

        String query =
                "SELECT id, first_name, last_name, username, email, role " +
                        "FROM users " +
                        "WHERE is_archived = 1";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query);
             ResultSet res = prep.executeQuery()) {

            while (res.next()) {

                users.add(new User(
                        res.getInt("id"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("username"),
                        res.getString("email"),
                        res.getString("role")
                ));
            }

        } catch (SQLException e) {
            System.out.println("View Archived Users: " + e.getMessage());
        }

        return users;
    }

    @Override
    public List<User> searchUsers(String keyword) {

        List<User> users = new ArrayList<>();

        String query =
                "SELECT id, first_name, last_name, username, email, role " +
                        "FROM users " +
                        "WHERE (LOWER(first_name) LIKE LOWER(?) " +
                        "OR LOWER(last_name) LIKE LOWER(?) " +
                        "OR LOWER(username) LIKE LOWER(?) " +
                        "OR LOWER(email) LIKE LOWER(?)) " +
                        "AND is_archived = 0";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");
            prep.setString(2, "%" + keyword + "%");
            prep.setString(3, "%" + keyword + "%");
            prep.setString(4, "%" + keyword + "%");

            ResultSet res = prep.executeQuery();

            while (res.next()) {

                users.add(new User(
                        res.getInt("id"),
                        res.getString("first_name"),
                        res.getString("last_name"),
                        res.getString("username"),
                        res.getString("email"),
                        res.getString("role")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Search User: " + e.getMessage());
        }

        return users;
    }
}