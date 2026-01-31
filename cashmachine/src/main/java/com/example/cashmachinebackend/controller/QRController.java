package com.example.cashmachinebackend.controller;

import com.example.cashmachinebackend.dto.QRCodeDTO;
import com.example.cashmachinebackend.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/qr")
public class QRController {

    @Autowired
    private QRService qrService;

    @GetMapping("/validate")
    public Map<String, Object> validateQR(@RequestParam String code) throws InterruptedException {
        Thread.sleep(200); // Artificial delay
        boolean isValid = qrService.validateQRCode(code);

        Map<String, Object> response = new HashMap<>();
        response.put("valid", isValid);

        if (isValid) {
            String customerName = qrService.getCustomerNameByQRCode(code);
            response.put("name", customerName);
        }

        return response;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQRCode(@RequestBody QRCodeDTO dto) {
        qrService.saveQRCode(dto);  // ✅ Pass the DTO directly
        return ResponseEntity.ok("QR Code added successfully");
    }
}
