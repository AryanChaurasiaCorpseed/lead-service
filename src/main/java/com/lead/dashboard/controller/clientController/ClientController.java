package com.lead.dashboard.controller.clientController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.service.LeadService;

@RestController
public class ClientController {
	
	  @Autowired
	  LeadService leadservice;

	  @PostMapping("/v1/lead/createLead")
	  public Lead  createClientInLead(@RequestParam Long leadId,@RequestParam String name,@RequestParam String contactNo,@RequestParam String email) {
		  
		  
		  Lead  fb =leadservice.createClientInLead(leadId, name , email, contactNo);
//		  Lead  fb =leadservice.createEnquiryLead( name, mobNo, desc);
//		 System.out.println(fb!=null?fb.getName():"NA------");
		 return fb;
	  }
	
}
