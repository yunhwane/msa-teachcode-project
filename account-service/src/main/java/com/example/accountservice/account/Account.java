package com.example.accountservice.account;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    private String accountId;
    private String name;
    private String nickName;
    private String email;
    private String password;
    private Role role;
    private EmailReceptionPolicy emailReceptionPolicy;

    public Account(String name, String nickName, String email, String password, EmailReceptionPolicy emailReceptionPolicy) {
        this.accountId = generateAccountId(email);
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.emailReceptionPolicy = emailReceptionPolicy;
        this.role = Role.USER;
    }

    private String generateAccountId(String email) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(email.getBytes());
            return "member_ak_" + Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }

}
