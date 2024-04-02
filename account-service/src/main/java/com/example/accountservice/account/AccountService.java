package com.example.accountservice.account;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountService {

    private final AccountPort accountPort;

    public AccountService(AccountPort accountPort) {
        this.accountPort = accountPort;
    }


    @Transactional
    public void addAccount(final AddAccountRequest addAccountRequest) {
        final Account account = new Account(addAccountRequest.name(), addAccountRequest.nickName(), addAccountRequest.email(), addAccountRequest.password(), addAccountRequest.emailReceptionPolicy());
        accountPort.save(account);
    }
}
