package com.example.cashmachinebackend.service;

import com.example.cashmachinebackend.model.Denomination;
import com.example.cashmachinebackend.model.QRCode;
import com.example.cashmachinebackend.model.Transaction;
import com.example.cashmachinebackend.repository.DenominationRepository;
import com.example.cashmachinebackend.repository.QRCodeRepository;
import com.example.cashmachinebackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DenominationService {

    @Autowired
    private DenominationRepository denominationRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Map<Integer, Integer> dispenseCashByQRCode(String qrCodeStr) {
        QRCode qrCode = qrCodeRepository.findByCode(qrCodeStr)
                .orElseThrow(() -> new RuntimeException("QR code not found"));

        if (!qrCode.isValid()) {
            throw new RuntimeException("QR code is expired or already used");
        }

        int amount = qrCode.getRewardAmount();

        // Log for debug
        System.out.println("Dispensing amount: $" + amount);

        // Calculate how to dispense the amount
        Map<Integer, Integer> result = calculateDispensedDenominations(amount);

        // Deduct dispensed notes from denomination quantities
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            int value = entry.getKey();
            int usedCount = entry.getValue();

            Denomination denom = denominationRepository.findByValue(value)
                    .orElseThrow(() -> new RuntimeException("Denomination not found for value: $" + value));

            int available = denom.getQuantity();
            if (available < usedCount) {
                throw new RuntimeException("Not enough notes for denomination $" + value);
            }

            denom.setQuantity(available - usedCount);
            denominationRepository.save(denom);
        }

        // Mark QR code as used
        qrCode.setUsed(true);
        qrCodeRepository.save(qrCode);

        // Log transaction
        Transaction txn = new Transaction();
        txn.setQrCode(qrCode);
        txn.setAmount((double) amount);
        txn.setTimestamp(LocalDateTime.now());
        transactionRepository.save(txn);

        return result;
    }

    private Map<Integer, Integer> calculateDispensedDenominations(int amount) {
        List<Denomination> denominations = denominationRepository.findByQuantityGreaterThanOrderByValueDesc(0);
        Map<Integer, Integer> result = new LinkedHashMap<>();

        for (Denomination d : denominations) {
            int available = d.getQuantity();
            int count = Math.min(amount / d.getValue(), available);

            if (count > 0) {
                result.put(d.getValue(), count);
                amount -= d.getValue() * count;
            }
        }

        if (amount > 0) {
            throw new RuntimeException("Not enough denominations to dispense full amount");
        }

        return result;
    }
}
