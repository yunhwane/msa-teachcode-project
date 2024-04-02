package com.example.accountservice.account;


import org.springframework.stereotype.Component;

@Component
public class AccountAdapter implements AccountPort {

    private final AccountRepository accountRepository;

    public AccountAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
