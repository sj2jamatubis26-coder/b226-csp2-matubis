package com.joysistvi.recording.repository;

import com.joysistvi.recording.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    boolean createUser(User user);

    boolean updateUser(String firstName,
                       String lastName,
                       String username,
                       String email,
                       String password,
                       String role,
                       int id);

    boolean deleteUser(int id);

    boolean archiveUser(int id);

    boolean restoreUser(int id);

    List<User> getArchivedUsers();

    List<User> searchUsers(String keyword);
    boolean userExists(String username);
}