package com.joysistvi.recording.repository;

import com.joysistvi.recording.model.User;

public interface LoginRepository {

    User login(String username, String password);


}