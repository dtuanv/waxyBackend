package com.waxy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}") // Injecting the email username
    private String fromAddress;
    public void sendSimpleMessage(String to, String subject, String text) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromAddress); // Ensure the "from" address is set
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
        }catch (Exception e){
            logger.error("Error sending email to {}: {}", to, e.getMessage(), e);
        }

    }
}