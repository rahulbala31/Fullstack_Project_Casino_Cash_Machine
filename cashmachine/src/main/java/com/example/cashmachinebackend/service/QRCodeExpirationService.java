package com.example.cashmachinebackend.service;
//import jakarta.annotation.PostConstruct;
import com.example.cashmachinebackend.model.QRCode;
import com.example.cashmachinebackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QRCodeExpirationService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Scheduled(fixedRate = 3600000) // every hour
    @Transactional  // ✅ Force proper transaction management


  //  @PostConstruct
  //  public void testExpireImmediately() {
  //      expireOldQRCodes(); // ✅ manually run once at startup for testing
//}
    public void expireOldQRCodes() {
        LocalDateTime cutoff = LocalDateTime.now().minusHours(24);
        List<QRCode> expiredQRCodes = qrCodeRepository.findByUsedFalseAndExpiredFalseAndIssuedAtBefore(cutoff);

        for (QRCode qr : expiredQRCodes) {
            qr.setExpired(true); // ✅ correctly marking as expired
        }

        qrCodeRepository.saveAll(expiredQRCodes);
        qrCodeRepository.flush(); // ✅ Force write to DB
    }

    @GetMapping("/admin/qrcodes/expired")
        public List<QRCode> getExpiredQRCodes() {
        return qrCodeRepository.findByExpiredTrue();
}


}
