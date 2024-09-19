package com.lead.dashboard.config;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lead.dashboard.service.MailSendService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class EmailServiceImpl implements MailSendService {


    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    
	@Autowired
	private TemplateEngine templateEngine;

    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(emailTo);
            helper.setCc(ccPersons);
            helper.setBcc(bccPersons);
            helper.setSubject("Kaushal Singh wants you to join Corpeed ERP");
            helper.setText("Kaushal Here");

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject,String text, Context context,String templateName) {
        try {
			String html = templateEngine.process(templateName, context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(emailTo);
            helper.setCc(ccPersons);
            helper.setBcc(bccPersons);
            helper.setSubject(subject);
			helper.setText(html, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendEmailWithAttachmentForVendor(String[] emailTo, String[] ccPersons, User from, String subject, String body, String attachmentPath, VendorQuotationRequest vendorQuotationRequest, User raisedBy) {

    }
}