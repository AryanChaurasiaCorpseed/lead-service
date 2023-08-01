package com.lead.dashboard.controller.clientController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.service.CommunicationService;
@RestController
public class CommunicationController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CommunicationService communicationService;
	

	  @PostMapping("/v1/communication/createMailCommunication")
	  public boolean mailsCommunication(@RequestParam String mailTo,@RequestParam String mailCc,@RequestParam String subject,@RequestParam String desc,@RequestParam Long leadId,@RequestParam Long clientId) {
		  boolean result = communicationService.mailsCommunication(mailTo, mailCc, subject, desc, leadId, clientId);
//		 System.out.println(fb!=null?fb.getName():"NA------");
		 return result;
	  }
	  
	  
	  @PostMapping("/v1/communication/deleteMailCommunication")
	  public boolean deleteMailCommunication(@RequestParam Long communicationId) {
		  boolean result = communicationService.deleteMailCommunication(communicationId);
//		 System.out.println(fb!=null?fb.getName():"NA------");
		 return result;
	  }


}
