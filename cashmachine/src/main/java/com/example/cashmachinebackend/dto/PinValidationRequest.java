package com.example.cashmachinebackend.dto;

public class PinValidationRequest {
    private String qrCode;
    private String pin;
    private String password;

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
