package com.example.cashmachinebackend.controller;

import com.example.cashmachinebackend.model.User;
import com.example.cashmachinebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String pin) {
        Optional<User> user = userService.authenticate(username, pin);
        return user.isPresent() ? "Login Successful!" : "Invalid Credentials!";
    }
}
