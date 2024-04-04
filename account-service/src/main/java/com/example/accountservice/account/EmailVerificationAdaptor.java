package com.example.accountservice.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailVerificationAdaptor implements EmailProducerPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic.email-verifications}")
    private String emailVerificationTopic;

    public EmailVerificationAdaptor(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void verifyEmailSend(String email) {
        EmailVerificationCommand message = new EmailVerificationCommand(email);
        kafkaTemplate.send(emailVerificationTopic, message);
    }
}
