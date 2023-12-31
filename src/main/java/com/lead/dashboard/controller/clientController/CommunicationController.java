package com.lead.dashboard.controller.clientController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.service.CommunicationService;
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class CommunicationController {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	CommunicationService communicationService;
	

	  @PostMapping("leadService/api/v1/communication/createMailCommunication")
	  public boolean mailsCommunication(@RequestParam String mailTo,@RequestParam String mailCc,@RequestParam String subject,@RequestParam String desc,@RequestParam Long leadId,@RequestParam Long clientId,@RequestParam boolean isSendBy) {
		  boolean result = communicationService.mailsCommunication(mailTo, mailCc, subject, desc, leadId, clientId,isSendBy);
		  return result;
	  }
	  
	  
	  @PostMapping("leadService/api/v1/communication/deleteMailCommunication")
	  public boolean deleteMailCommunication(@RequestParam Long communicationId) {
		  boolean result = communicationService.deleteMailCommunication(communicationId);
		  return result;
	  }


}
