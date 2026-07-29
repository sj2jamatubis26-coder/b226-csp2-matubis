package com.joysistvi.recording.repository;

import com.joysistvi.recording.config.DbConnection;
import com.joysistvi.recording.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepositoryImpl implements LoginRepository {

    private final DbConnection dbConnection;

    public LoginRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public User login(String username, String password) {

        String sql = """
                SELECT *
                FROM users
                WHERE username = ?
                AND password = ?
                AND is_archived = 0
                """;

        try (Connection connection = dbConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
        }

        return null;
    }
}