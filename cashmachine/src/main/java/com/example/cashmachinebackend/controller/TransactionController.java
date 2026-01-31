package com.example.cashmachinebackend.controller;

import com.example.cashmachinebackend.model.Transaction;
import com.example.cashmachinebackend.model.User;
import com.example.cashmachinebackend.repository.TransactionRepository;
import com.example.cashmachinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public String createTransaction(@RequestParam Long userId, @RequestParam Double amount) {
        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setTimestamp(LocalDateTime.now());
        tx.setUser(userRepository.findById(userId).orElseThrow());
        transactionRepository.save(tx);
        return "Transaction recorded!";
    }

    // ✅ NEW: Get all transactions for a user
    @GetMapping("/user")
    public List<Transaction> getTransactionsByUserId(@RequestParam Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return transactionRepository.findByUser(user);
    }
}
