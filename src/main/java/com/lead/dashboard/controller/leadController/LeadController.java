package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.config.EmailServiceImpl;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.service.LeadService;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

//@Tag(name = "Lead", description = "Lead management APIs")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")


public class LeadController {

	@Autowired
	LeadService leadservice;

	@Autowired
	EmailServiceImpl emailServiceImpl;


	@PostMapping("/v1/lead/customerLeadCreated")
	public ResponseEntity<Lead> createLead(@RequestBody LeadDTO leadDTO) 
	{
		if (leadDTO!=null) {
			try {
				Lead createdLead = leadservice.createLead(leadDTO);
				return new ResponseEntity<>(createdLead, HttpStatus.CREATED);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create lead", e);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/v1/lead/getAllLead")
	public ResponseEntity <List<Lead>> getAllLead()
	{
		List<Lead> alllead= leadservice.getAllActiveCustomerLead();
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}

	@PutMapping("/v1/lead/updateCustomerLead")
	public ResponseEntity<Lead> updateCustomerLeadData(@RequestBody UpdateLeadDto updateLeadDto)
	{
		System.out.println("Hit");
		Lead updatedLeadData = leadservice.updateLeadData(updateLeadDto);
		return new ResponseEntity<>(updatedLeadData,HttpStatus.OK);
	}

	@DeleteMapping("/v1/lead/deleteLead")
	public  boolean  deleteLead(@RequestParam Long leadId)
	{
		boolean  deletedLead = leadservice.deleteLead(leadId);
		return deletedLead;
	}

	@DeleteMapping("/v1/lead/sendMailInLead")
	public  boolean  sendMailInLead( String to, String subject, String text){
		emailServiceImpl.sendSimpleMessage(to,  subject,  text);
		return true;  
	}
	@GetMapping("/v1/lead/getSingleLeadData")
	public ResponseEntity <Lead> getSingleLeadData(@RequestParam Long leadId)
	{
		Lead alllead= leadservice.getSingleLeadData(leadId);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}



}

