package com.example.accountservice.account;


import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private final EmailProducerPort emailProducerPort;

    public EmailService(EmailProducerPort emailProducerPort) {
        this.emailProducerPort = emailProducerPort;
    }

    public void verifyEmail(VerifyEmailRequest verifyEmailRequest) {
       emailProducerPort.verifyEmailSend(verifyEmailRequest.email());
    }
}
