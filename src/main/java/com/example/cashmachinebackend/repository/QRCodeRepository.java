package com.example.cashmachinebackend.repository;

import com.example.cashmachinebackend.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    Optional<QRCode> findByCode(String code);

    // Used for expiration service
    List<QRCode> findByUsedFalseAndExpiredFalseAndIssuedAtBefore(LocalDateTime time);


    // Used for cleanup service
    void deleteByUsedTrueAndIssuedAtBefore(LocalDateTime cutoff);

    List<QRCode> findByExpiredTrue();  //admin view

}
