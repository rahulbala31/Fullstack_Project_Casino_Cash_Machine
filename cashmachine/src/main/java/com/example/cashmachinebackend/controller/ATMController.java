package com.example.cashmachinebackend.controller;

import com.example.cashmachinebackend.model.Denomination;
import com.example.cashmachinebackend.model.QRCode;
import com.example.cashmachinebackend.repository.DenominationRepository;
import com.example.cashmachinebackend.repository.QRCodeRepository;
import com.example.cashmachinebackend.service.DenominationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    @Autowired
    private DenominationRepository denominationRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;


    @Autowired
    private DenominationService denominationService;

    // 🔹 GET available denominations (quantity > 0)
    @GetMapping("/available-denominations")
    public List<Denomination> getAvailable() {
        return denominationRepository.findByQuantityGreaterThanOrderByValueDesc(0);
    }

    // 🔹 POST dispense endpoint
  @PostMapping("/dispense")
    public ResponseEntity<?> dispense(@RequestParam String qrCode) {
        try {
            if (qrCode == null || qrCode.isBlank()) {
                return ResponseEntity.badRequest().body("Invalid QR code");
            }

            Map<Integer, Integer> result = denominationService.dispenseCashByQRCode(qrCode);

            if (result == null || result.isEmpty()) {
                return ResponseEntity.badRequest().body("No denominations available or QR code already used.");
            }

            return ResponseEntity.ok(result);

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unexpected error occurred");
        }
    }


    @GetMapping("/qr-info")
    public Map<String, Object> getQRInfo(@RequestParam String qrCode) {
        QRCode qr = qrCodeRepository.findByCode(qrCode)
            .orElseThrow(() -> new RuntimeException("QR code not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("amount", qr.getRewardAmount());
        response.put("issuedAt", qr.getIssuedAt());
        return response;
    }


    


}
