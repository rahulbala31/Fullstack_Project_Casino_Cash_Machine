package com.example.cashmachinebackend.repository;

import com.example.cashmachinebackend.model.Transaction;
import com.example.cashmachinebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);
}
