package com.waxy.controller;

import com.waxy.dto.EmailRequest;
import com.waxy.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api")
    public class EmailController {

        @Autowired
        private EmailService emailService;

        @PostMapping("/send-email")
        public void sendEmail(@RequestBody EmailRequest emailRequest) {
            emailService.sendSimpleMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
        }
    }

