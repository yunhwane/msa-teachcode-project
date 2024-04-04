package com.example.accountservice.account;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Transactional
class AccountServiceTest {


    @Autowired private AccountRepository accountRepository;
    @Autowired private AccountService accountService;
    @Test
    @DisplayName("중복된 이메일이 있을 때 true를 반환한다.")
    void shouldReturnTrueForDuplicateEmail() {
        // given
        String existingEmail = "이메일@com";
        Account account = 회원등록모델생성(existingEmail);
        accountRepository.save(account);
        // when
        boolean isEmailDuplicate = accountService.checkEmailDuplication(이메일요청모델생성());
        // then
        Assertions.assertThat(isEmailDuplicate).isTrue();
    }
    private static CheckEmailDuplicationRequest 이메일요청모델생성() {
        return new CheckEmailDuplicationRequest("이메일@com");
    }

    @Test
    @DisplayName("중복된 이메일이 없을 때 false를 반환한다.")
    void shouldReturnFalseForUniqueEmail() {
        // given
        String existingEmail = "이메일@coms";
        Account account = 회원등록모델생성(existingEmail);
        accountRepository.save(account);

        // when
        boolean isEmailDuplicate = accountService.checkEmailDuplication(이메일요청모델생성());

        // then
        Assertions.assertThat(isEmailDuplicate).isFalse();
    }

    private static Account 회원등록모델생성(String email) {
        return new Account("이름", "닉네임", email,  "password", EmailReceptionPolicy.YES);
    }

}
