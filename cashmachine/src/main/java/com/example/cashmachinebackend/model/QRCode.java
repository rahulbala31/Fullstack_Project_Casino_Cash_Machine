package com.example.cashmachinebackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String customerName;

    @Column(nullable = false)
    private int rewardAmount;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean used = false;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean expired = false;

    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return !used && !expired &&
               issuedAt.isBefore(now) &&
               issuedAt.isAfter(now.minusHours(24));
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public int getRewardAmount() { return rewardAmount; }
    public void setRewardAmount(int rewardAmount) { this.rewardAmount = rewardAmount; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public boolean isExpired() { return expired; }
    public void setExpired(boolean expired) { this.expired = expired; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
