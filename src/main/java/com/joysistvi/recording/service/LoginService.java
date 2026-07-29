package com.joysistvi.recording.service;

import com.joysistvi.recording.model.User;
import com.joysistvi.recording.repository.LoginRepository;

public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public User login(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        return loginRepository.login(username, password);
    }
}