package com.example.mercury.service;

import com.example.mercury.entitiy.User;

import java.util.Optional;

public interface UserService {
    boolean addUser(User user);
    Optional<User> getUserByUsername(String userName);
}
