package com.example.cashmachinebackend.service;

import com.example.cashmachinebackend.dto.QRCodeDTO;
import com.example.cashmachinebackend.model.QRCode;
import com.example.cashmachinebackend.model.User;
import com.example.cashmachinebackend.repository.QRCodeRepository;
import com.example.cashmachinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QRService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean validateQRCode(String code) {
        Optional<QRCode> optionalQR = qrCodeRepository.findByCode(code);
        return optionalQR.isPresent() && !optionalQR.get().isUsed() && !optionalQR.get().isExpired();
    }

    public String getCustomerNameByQRCode(String code) {
        Optional<QRCode> optionalQR = qrCodeRepository.findByCode(code);
        return optionalQR.map(QRCode::getCustomerName).orElse("Customer");
    }

    public void saveQRCode(QRCodeDTO dto) {
        QRCode qrCode = new QRCode();
        qrCode.setCode(dto.getCode());
        qrCode.setCustomerName(dto.getCustomerName());
        qrCode.setRewardAmount(dto.getRewardAmount());
        qrCode.setIssuedAt(dto.getIssuedAt());
        qrCode.setUsed(dto.isUsed());
        qrCode.setExpired(dto.isExpired());
        qrCode.setPassword(dto.getPassword());

        // ✅ Convert userId from DTO to User entity
        Optional<User> optionalUser = userRepository.findById(dto.getUserId());
        if (optionalUser.isPresent()) {
            qrCode.setUser(optionalUser.get());
        } else {
            throw new IllegalArgumentException("Invalid user ID: " + dto.getUserId());
        }

        qrCodeRepository.save(qrCode);
        qrCode.setPassword(dto.getPassword());

    }
}
