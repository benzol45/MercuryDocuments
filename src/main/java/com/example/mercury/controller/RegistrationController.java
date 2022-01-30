package com.example.mercury.controller;

import com.example.mercury.entitiy.User;
import com.example.mercury.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "Registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        boolean result = userService.addUser(user);
        if (result) {
            return "redirect:/login";
        } else
        {
            return "redirect:/registration?error";
        }
    }
}
