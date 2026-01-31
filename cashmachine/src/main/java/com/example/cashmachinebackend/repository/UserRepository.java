package com.example.cashmachinebackend.repository;

import com.example.cashmachinebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameAndPin(String username, String pin);
}
