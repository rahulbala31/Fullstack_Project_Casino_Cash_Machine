package com.example.cashmachinebackend.controller;

import com.example.cashmachinebackend.dto.PinValidationRequest;
import com.example.cashmachinebackend.model.QRCode;
import com.example.cashmachinebackend.model.User;
import com.example.cashmachinebackend.repository.QRCodeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @PostMapping("/validate-pin")
    public String validatePin(@RequestBody PinValidationRequest request) {
        QRCode qrCode = qrCodeRepository.findByCode(request.getQrCode())
                .orElseThrow(() -> new RuntimeException("QR code not found"));

        User user = qrCode.getUser();
        if (user == null) {
            return "❌ No user associated with this QR code.";
        }

        boolean isUserPinValid = String.valueOf(user.getPin()).equals(request.getPin());
        boolean isQrPasswordValid = qrCode.getPassword().equals(request.getPassword());

        if (isUserPinValid && isQrPasswordValid) {
            return "✅ PIN and password validated. Welcome, " + user.getUsername();
        } else if (!isUserPinValid) {
            return "❌ Incorrect PIN. Try again.";
        } else {
            return "❌ Incorrect QR code password.";
        }
    }

}
