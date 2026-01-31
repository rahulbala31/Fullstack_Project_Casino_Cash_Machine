package com.example.cashmachinebackend.controller;


import com.example.cashmachinebackend.service.DenominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/denominations")
public class DenominationController {

    @Autowired
    private DenominationService denominationService;

    @PostMapping("/dispense-by-qr")
    public ResponseEntity<?> dispenseByQRCode(@RequestBody Map<String, String> request) {
        try {
            String qrCode = request.get("qrCode");

            if (qrCode == null || qrCode.isBlank()) {
                return ResponseEntity.badRequest().body("Invalid QR code");
            }

            Map<Integer, Integer> result = denominationService.dispenseCashByQRCode(qrCode);
            return ResponseEntity.ok(result);

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error occurred");
        }
    }
}
