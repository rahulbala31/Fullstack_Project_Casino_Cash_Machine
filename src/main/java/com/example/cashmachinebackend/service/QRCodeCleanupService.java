package com.example.cashmachinebackend.service;

import com.example.cashmachinebackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QRCodeCleanupService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    // Run daily to delete expired and old QR codes
    @Scheduled(cron = "0 0 3 * * ?") // 3:00 AM daily
    public void cleanupExpiredQRCodes() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(30);
        qrCodeRepository.deleteByUsedTrueAndIssuedAtBefore(cutoff);
    }
    
}
