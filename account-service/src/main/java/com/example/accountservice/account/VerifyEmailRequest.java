package com.example.accountservice.account;

import lombok.Getter;


public record VerifyEmailRequest (String email) {
    public VerifyEmailRequest {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
    }
}
