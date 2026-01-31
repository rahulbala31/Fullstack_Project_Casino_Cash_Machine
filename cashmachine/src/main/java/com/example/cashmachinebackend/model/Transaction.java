package com.example.cashmachinebackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "qr_code_id")
    private QRCode qrCode;  // ✅ Needed for setQrCode()

    private String denominationBreakdown;  // ✅ Needed for setDenominationBreakdown()

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QRCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCode qrCode) {
        this.qrCode = qrCode;
    }

    public String getDenominationBreakdown() {
        return denominationBreakdown;
    }

    public void setDenominationBreakdown(String denominationBreakdown) {
        this.denominationBreakdown = denominationBreakdown;
    }
}
