package com.example.cashmachinebackend.model;

import jakarta.persistence.*;

@Entity
public class Denomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int value;       // Denomination value, e.g., 100, 50

    @Column(nullable = false)
    private int quantity;    // Number of notes available

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
