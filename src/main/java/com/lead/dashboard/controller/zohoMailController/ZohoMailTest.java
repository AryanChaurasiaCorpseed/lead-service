package com.lead.dashboard.controller.zohoMailController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lead.dashboard.config.MailSendSerivceImplZoho;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/leadService/")
public class ZohoMailTest {
	@Autowired
	MailSendSerivceImplZoho mailSendSerivceImplZoho;
	
	
	@PostMapping("api/v1/mailSendWithZoho")
	public void  mailSendWithZoho(){
		String [] emailTo= {"aryan.chaurasia@corpseed.com"};
		String [] emailCc= {"navjot.singh@corpseed.com "};
		String[] bccPersons= {"avnish.kumar@corpseed.com "};
//		mailSendSerivceImplZoho.sendEmail(emailTo, emailCc, bccPersons);
		mailSendSerivceImplZoho.sendEmail("aryan.chaurasia@corpseed.com","erp@corpseed.com", "testing");
	}
}
