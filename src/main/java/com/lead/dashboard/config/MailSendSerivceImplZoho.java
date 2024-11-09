package com.lead.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.lead.dashboard.service.MailSendService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class MailSendSerivceImplZoho {
//implements MailSendService {
//	 @Autowired
//	    private JavaMailSender javaMailSender;
//
//	    @Value("${spring.mail.username}")
//	    private String fromEmail;
//	    
//		@Autowired
//		private TemplateEngine templateEngine;
//		
//		public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons) {
//	        try {
//	            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//	            helper.setFrom(fromEmail);
//	            helper.setTo(emailTo);
//	            helper.setCc(ccPersons);
//	            helper.setBcc(bccPersons);
//	            helper.setSubject("Aryan wants you to join Corpeed ERP");
//	            helper.setText("Aryan Here");
//
//	            javaMailSender.send(mimeMessage);
//	        } catch (MessagingException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    
//	    
//	    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject,String text, Context context,String templateName) {
//	        try {
//				String html = templateEngine.process(templateName, context);
//
//	            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//	            helper.setFrom(fromEmail);
//	            helper.setTo(emailTo);
//	            helper.setCc(ccPersons);
//	            helper.setBcc(bccPersons);
//	            helper.setSubject(subject);
//				helper.setText(html, true);
//	            javaMailSender.send(mimeMessage);
//	        } catch (MessagingException e) {
//	            e.printStackTrace();
//	        }
//	    }
	
	private JavaMailSender javaMailSender;

    @Autowired
    public  MailSendSerivceImplZoho(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("erp@corpseed.com");

        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }
}
