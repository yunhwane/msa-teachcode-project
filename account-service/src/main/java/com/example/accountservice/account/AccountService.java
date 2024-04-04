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
    public void addAccount(final AddAccountRequest request) {
        final Account account = new Account(request.name(), request.nickName(), request.email(), request.password(), request.emailReceptionPolicy());
        accountPort.save(account);
    }

    @Transactional(readOnly = true)
    public boolean checkEmailDuplication(CheckEmailDuplicationRequest request) {
        return accountPort.findAccountByEmail(request.email()).isPresent();
    }
}
