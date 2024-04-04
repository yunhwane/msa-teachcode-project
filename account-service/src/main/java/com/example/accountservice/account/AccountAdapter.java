package com.example.accountservice.account;


import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountAdapter implements AccountPort {

    private final AccountRepository accountRepository;
    private final AccountRepositoryCustom accountRepositoryCustom;

    public AccountAdapter(AccountRepository accountRepository, AccountRepositoryCustom accountRepositoryCustom) {
        this.accountRepository = accountRepository;
        this.accountRepositoryCustom = accountRepositoryCustom;
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountByEmail(String email) {
        return accountRepositoryCustom.findAccountByEmail(email);
    }
}
