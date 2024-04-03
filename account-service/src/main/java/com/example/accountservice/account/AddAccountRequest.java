package com.example.accountservice.account;

import static com.mysema.commons.lang.Assert.hasText;
import static org.apache.http.util.Asserts.notNull;

record AddAccountRequest(String name, String nickName, String email, String password,
                         EmailReceptionPolicy emailReceptionPolicy) {
    AddAccountRequest {
        hasText(name, "이름은 필수 입니다.");
        hasText(nickName, "닉네임은 필수 입니다.");
        hasText(email, "이메일은 필수 입니다.");
        hasText(password, "비밀번호는 필수 입니다.");
        notNull(emailReceptionPolicy, "이메일 수신 정책은 필수 입니다.");
        if(emailReceptionPolicy == EmailReceptionPolicy.NO) {
            throw new IllegalArgumentException("이메일 수신 정책에 동의 해야 합니다.");
        }
    }
}
