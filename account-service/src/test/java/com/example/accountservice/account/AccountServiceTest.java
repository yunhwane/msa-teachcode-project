package com.example.accountservice.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.mysema.commons.lang.Assert.hasText;
import static org.apache.http.util.Asserts.notNull;


/**
 */
class AccountServiceTest {

    private AccountService accountService;
    private AccountCommandPort accountCommandPort;
    private AccountRepository accountRepository;
    


    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepository();
        accountCommandPort = new AccountCommandAdapter(accountRepository);
        accountService = new AccountService(accountCommandPort);
    }
    @Test
    void 회원등록() {
        // given
        final AddAccountRequest addAccountRequest = new AddAccountRequest("이름", "닉네임", "이메일@com", "password", EmailReceptionPolicy.YES);

        // then
        accountService.addAccount(addAccountRequest);
    }

    private record AddAccountRequest(String name, String nickName, String email, String password, EmailReceptionPolicy emailReceptionPolicy) {
        private AddAccountRequest {
            hasText(name, "이름은 필수 입니다.");
            hasText(nickName, "닉네임은 필수 입니다.");
            hasText(email, "이메일은 필수 입니다.");
            hasText(password, "비밀번호는 필수 입니다.");
            notNull(emailReceptionPolicy, "이메일 수신 정책은 필수 입니다.");
        }
    }

    private enum EmailReceptionPolicy {
        YES
    }

    private enum Role {
        CLIENT
    }

    public class AccountService {

        private final AccountCommandPort accountCommandPort;

        public AccountService(AccountCommandPort accountCommandPort) {
            this.accountCommandPort = accountCommandPort;
        }

        public void addAccount(final AddAccountRequest addAccountRequest) {
            final Account account = new Account(addAccountRequest.name(), addAccountRequest.nickName(), addAccountRequest.email(), addAccountRequest.password(), addAccountRequest.emailReceptionPolicy());
            accountCommandPort.save(account);
        }
    }

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

    public interface AccountCommandPort {
        public void save(final Account account);
    }

    public class AccountCommandAdapter implements AccountCommandPort {

        private final AccountRepository accountRepository;

        public AccountCommandAdapter(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;
        }

        @Override
        public void save(Account account) {
            accountRepository.save(account);
        }
    }

    public class AccountRepository {

        private Map<Long, Account> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(final Account account) {
            // 저장 로직
            account.assignId(++sequence);
            persistence.put(account.getId(), account);
        }
    }
}
