package com.joysistvi.recording.controller;

import com.joysistvi.recording.model.User;
import com.joysistvi.recording.service.LoginService;

public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public User login(String username, String password) {
        return loginService.login(username, password);
    }

}
