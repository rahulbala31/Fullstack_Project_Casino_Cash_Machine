package com.example.cashmachinebackend.service;

import com.example.cashmachinebackend.model.User;
import com.example.cashmachinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String username, String pin) {
        return userRepository.findByUsernameAndPin(username, pin);
    }
}