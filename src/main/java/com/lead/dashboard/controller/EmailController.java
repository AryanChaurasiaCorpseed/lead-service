package com.lead.dashboard.controller;


import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private MailSendSerivceImpl mailSendSerivce;

    @PostMapping("/send")
    public String sendEmail(String[] ccPersons) {
        String emailTo = "kaushlendra.pratap@corpseed.com";
//        String[] ccPersons = {"kaushlendra.pratap@corpseed.com", "rahul.jain@corpseed.com"};
        String[] bccPersons = {"kaushlendra.pratap@corpseed.com", "rahul.jain@corpseed.com"};

        mailSendSerivce.sendEmail(emailTo, ccPersons, bccPersons);

        return "Email sent successfully!";
    }
}