package com.example.accountservice.account;

public class Account {

    private Long id;
    private String name;
    private String nickName;
    private String email;
    private String password;

    private Role role;

    private EmailReceptionPolicy emailReceptionPolicy;

    public Account(String name, String nickName, String email, String password, EmailReceptionPolicy emailReceptionPolicy) {
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.emailReceptionPolicy = emailReceptionPolicy;
        this.role = Role.CLIENT;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public EmailReceptionPolicy getEmailReceptionPolicy() {
        return emailReceptionPolicy;
    }

    public Long getId() {
        return id;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
