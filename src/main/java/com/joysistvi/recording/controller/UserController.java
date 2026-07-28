package com.joysistvi.recording.controller;

import com.joysistvi.recording.model.User;
import com.joysistvi.recording.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean addUser(String firstName, String lastName, String username,
                           String email, String password, String role) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        return userService.addUser(user);
    }

    public List<User> listUsers() {
        return userService.listUsers();
    }

    public boolean updateUser(String firstName, String lastName, String username, String email,
                              String password, String role, int id) {
        return userService.updateUser(firstName, lastName, username, email, password, role, id);
    }

    public boolean deleteUser(int id) {
        return userService.deleteUser(id);
    }

    public boolean archiveUser(int id) {
        return userService.archiveUser(id);
    }

    public boolean restoreUser(int id) {
        return userService.restoreUser(id);
    }

    public List<User> listArchivedUsers() {
        return userService.listArchivedUsers();
    }

    public List<User> searchUsers(String keyword) {
        return userService.searchUsers(keyword);
    }
}