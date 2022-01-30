package com.example.mercury.repository;

import com.example.mercury.entitiy.User;

public interface UserRepository {
    void addUser(User user);
    User getUserByUsername(String userName);
    int getCount();
}
