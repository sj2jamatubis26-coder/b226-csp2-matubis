package com.joysistvi.recording.service;

import com.joysistvi.recording.model.User;

import java.util.List;

import com.joysistvi.recording.repository.UserRepository;

public class UserService {

    private final UserRepository userRepo;

    // Constructor injection
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public boolean addUser(User user) {
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            System.out.println("First name cannot be empty.");
            return false;
        }

        return userRepo.createUser(user);
    }

    public List<User> listUsers() {
        return userRepo.getAllUsers();
    }

    public boolean updateUser(String firstName, String lastName, String username, String email,
                              String password, String role, int id) {

        if (firstName == null || firstName.trim().isEmpty()) {
            System.out.println("First name cannot be empty.");
            return false;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            System.out.println("Last name cannot be empty.");
            return false;
        }

        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty.");
            return false;
        }

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email cannot be empty.");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }

        if (role == null || role.trim().isEmpty()) {
            System.out.println("Role cannot be empty.");
            return false;
        }

        return userRepo.updateUser( firstName,lastName,username,email,password,role,id);
    }

    public boolean deleteUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid User ID.");
            return false;
        }

        return userRepo.deleteUser(id);
    }

    public boolean archiveUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid User ID.");
            return false;
        }

        return userRepo.archiveUser(id);
    }

    public boolean restoreUser(int id) {

        if (id <= 0) {
            System.out.println("Invalid User ID.");
            return false;
        }

        return userRepo.restoreUser(id);
    }

    public List<User> listArchivedUsers() {
        return userRepo.getArchivedUsers();
    }

    public List<User> searchUsers(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty.");
            return List.of();
        }

        return userRepo.searchUsers(keyword);
    }

}
