package com.example.accountservice.account;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final EmailService emailService;

    public AccountController(AccountService accountService, EmailService emailService) {
        this.accountService = accountService;
        this.emailService = emailService;
    }

    @PostMapping()
    public ResponseEntity<?> addAccount(@RequestBody AddAccountRequest addAccountRequest) {
        accountService.addAccount(addAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("회원가입에 성공하였습니다."));
    }

    @PostMapping("/check-email")
    public ResponseEntity<ApiResponse<Boolean>> checkEmailDuplication(@RequestBody CheckEmailDuplicationRequest checkEmailDuplicationRequest) {

        boolean isCheckEmailDuplication = accountService.checkEmailDuplication(checkEmailDuplicationRequest);

        if(isCheckEmailDuplication){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(false, "중복된 이메일 입니다. 다른 이메일을 입력해주세요.", null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, null, null));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest verifyEmailRequest) {
        emailService.verifyEmail(verifyEmailRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("이메일 전송되었습니다. 이메일을 확인해주세요."));
    }

}
