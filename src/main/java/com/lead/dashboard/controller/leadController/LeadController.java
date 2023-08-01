package com.lead.dashboard.controller.leadController;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.config.EmailServiceImpl;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.dto.LeadDto;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.service.LeadService;
import com.lead.dashboard.util.UrlsMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag(name = "Lead", description = "Lead management APIs")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")


public class LeadController {

  @Autowired
  LeadService leadservice;

  @Autowired
  EmailServiceImpl emailServiceImpl;
	
	

  @PostMapping("/v1/lead/createLead")
  public Lead  createLead(@RequestParam String name,@RequestParam String mobNo,@RequestParam String desc) {
	  Lead  fb =leadservice.createEnquiryLead( name, mobNo, desc);
//	 System.out.println(fb!=null?fb.getName():"NA------");
	 return fb;
  }
  @PostMapping("/v2/lead/createLead")
  public Lead  createLead(@RequestBody LeadDto leadDto) {
	  Lead  fb =leadservice.createEnquiryLead(leadDto);
//	 System.out.println(fb!=null?fb.getName():"NA------");
	 return fb;
  }
  
  @GetMapping("/v1/lead/getAllLead")
  public  List<Lead>  getLead() {
  List<Lead> fb = leadservice.getLead();
//	 System.out.println(fb!=null?fb.getName():"NA------");
	 return fb;
  }
  @GetMapping("/v1/lead/getLead")
  public  Lead  getLeadById(@RequestParam Long id){
	  Lead fb = leadservice.getSingleLead(id);
	 return fb;
  }
  @PutMapping("/v1/lead/updateLeadData")
  public  Lead  updateLeadData(@RequestParam UpdateLeadDto UpdateLeadDto){
	  Lead fb = leadservice.updateLeadData(UpdateLeadDto);
	  return fb;
  }
  
  @DeleteMapping("/v1/lead/deleteLeadData")
  public  boolean  deleteLeadData(@RequestParam Long leadId){
      boolean  fb = leadservice.deleteLeadData(leadId);
	  return fb;
  }
  
  
  @DeleteMapping("/v1/lead/sendMailInLead")
  public  boolean  sendMailInLead( String to, String subject, String text){
	  emailServiceImpl.sendSimpleMessage(to,  subject,  text);
	  
	  return true;
	  
  }


}

