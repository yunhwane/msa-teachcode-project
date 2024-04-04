package com.example.accountservice.account;

import java.util.Optional;

public interface AccountPort {
    void save(final Account account);
    Optional<Account> findAccountByEmail(String email);
}
