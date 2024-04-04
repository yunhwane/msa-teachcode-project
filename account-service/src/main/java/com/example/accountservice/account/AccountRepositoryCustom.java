package com.example.accountservice.account;


import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccountRepositoryCustom(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Optional<Account> findAccountByEmail(String email) {

        QAccount account = QAccount.account;

        return Optional.ofNullable(queryFactory.selectFrom(account)
                .where(account.email.eq(email))
                .fetchOne());
    }
}
