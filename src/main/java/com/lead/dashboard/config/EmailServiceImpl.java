package com.lead.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
    private   JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendSimpleMessage(
      String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("aryanchaurasia11298@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        javaMailSender.send(message);
    }
}