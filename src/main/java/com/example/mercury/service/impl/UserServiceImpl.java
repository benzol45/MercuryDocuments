package com.example.mercury.service.impl;

import com.example.mercury.entitiy.User;
import com.example.mercury.repository.UserRepository;
import com.example.mercury.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(User user) {
        if (userRepository.getUserByUsername(user.getUsername())!=null) {
            return false;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(User.Role.USER);

        userRepository.addUser(user);

        return true;
    }

    @Override
    public Optional<User> getUserByUsername(String userName) {
        return Optional.of(userRepository.getUserByUsername(userName));
    }
}
