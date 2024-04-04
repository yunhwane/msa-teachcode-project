package com.example.accountservice.account;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class EmailVerificationCommand {
    public static final Long EMAIL_VERIFICATION_EXPIRATION_MINUTES = 5L;

    private String email;
    private Timestamp expiredAt;

    public EmailVerificationCommand(String email) {
        this.email = email;
        this.expiredAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(EMAIL_VERIFICATION_EXPIRATION_MINUTES));
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getExpiredAt() {
        return expiredAt;
    }
}
