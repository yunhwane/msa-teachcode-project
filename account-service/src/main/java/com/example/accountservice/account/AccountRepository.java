package com.example.accountservice.account;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class AccountRepository {

    private Map<Long, Account> persistence = new HashMap<>();
    private Long sequence = 0L;

    public void save(final Account account) {
        // 저장 로직
        account.assignId(++sequence);
        persistence.put(account.getId(), account);
    }
}
