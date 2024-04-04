package com.example.accountservice.account;

public record CheckEmailDuplicationRequest (String email) {

    public CheckEmailDuplicationRequest {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 필수 입니다.");
        }
    }

}
