package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.service.MailSendService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

@Service
public class MailSendSerivceImpl implements MailSendService {

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
            helper.setSubject("Aryan wants you to join Corpeed ERP");
            helper.setText("Aryan Here");

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
    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject, Context context,String templateName) {
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
    
    public void sendEmail(String[] emailTo,String subject,String text, Context context,String templateName) {
        try {
			String html = templateEngine.process(templateName, context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(emailTo);
            helper.setSubject(subject);
			helper.setText(html, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void sendEmailWithAttachmentForVendor(String[] emailTo, String[] ccPersons, String subject,
                                                 String body, VendorQuotationRequest vendorQuotationRequest,
                                                 User mailSentBy) {
        try {
            // Validate email addresses
            validateEmailAddresses(emailTo, "To");
            validateEmailAddresses(ccPersons, "Cc");

            // Create and configure the email
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail); // You may change the "fromEmail" as per your application logic
            helper.setTo(emailTo);
            helper.setCc(ccPersons);
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariable("clientName", vendorQuotationRequest.getClientName());
            context.setVariable("urlsName", vendorQuotationRequest.getServiceName());
            context.setVariable("sentBy", mailSentBy.getFullName());
            context.setVariable("quotationAmount", vendorQuotationRequest.getQuotationAmount());
            context.setVariable("quotationFilePath", vendorQuotationRequest.getQuotationFilePath());

            String htmlContent = templateEngine.process("vendor_email_template", context);
            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
            System.out.println("Email sent successfully to: " + Arrays.toString(emailTo));

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email due to MessagingException: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }





    private void validateEmailAddresses(String[] emailAddresses, String type) {
        for (String email : emailAddresses) {
            if (email != null && !email.isEmpty()) {
                if (!email.contains("@") || !email.contains(".")) {
                    throw new IllegalArgumentException(type + " email is invalid: " + email);
                }
            }
        }
    }



}

