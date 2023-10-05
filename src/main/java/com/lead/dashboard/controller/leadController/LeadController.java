package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.service.StatusService;
import com.lead.dashboard.util.UrlsMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lead.dashboard.config.EmailServiceImpl;
import com.lead.dashboard.config.SecurityFeignClient;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.service.LeadService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@Tag(name = "Lead", description = "Lead management APIs")
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

// @RequestMapping("/leadService/api")
public class LeadController {

//	private static final String CreateServiceDetails = null;

	@Autowired
	LeadService leadservice;

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Autowired
	StatusService statusService;
	
	@Autowired
	SecurityFeignClient securityFeignClient;

	
//	@GetMapping("/v1/lead/test")
	@GetMapping(UrlsMapping.TEST)
	public String test()
	{
		return securityFeignClient.test();		 
	}

	@PostMapping(UrlsMapping.CREATE_LEAD)
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

//	@GetMapping("/v1/lead/getAllLead")
	@GetMapping(UrlsMapping.GET_ALL_LEAD)
	public ResponseEntity <List<Lead>> getAllLead(Long userId)
	{
		List<Lead> alllead= leadservice.getAllActiveCustomerLead(userId);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}

//	@PutMapping("/v1/lead/updateLead")
	@PutMapping(UrlsMapping.UPDATE_LEAD)
	public ResponseEntity<Lead> updateCustomerLeadData(@RequestBody UpdateLeadDto updateLeadDto)
	{
		System.out.println("Hit");
		Lead updatedLeadData = leadservice.updateLeadData(updateLeadDto);
		return new ResponseEntity<>(updatedLeadData,HttpStatus.OK);
	}

//	@DeleteMapping("/v1/lead/deleteLead")
	@DeleteMapping(UrlsMapping.DELETE_LEAD)
	public  boolean  deleteLead(@RequestParam Long leadId)
	{
		boolean  deletedLead = leadservice.deleteLead(leadId);
		return deletedLead;
	}

//	@DeleteMapping("/v1/lead/sendMailInLead")
	@PostMapping(UrlsMapping.SEND_MAIL_IN_LEAD)
	public  boolean  sendMailInLead( String to, String subject, String text){
		emailServiceImpl.sendSimpleMessage(to,  subject,  text);
		return true;
	}
//	@GetMapping("/v1/lead/getSingleLeadData")
	@GetMapping(UrlsMapping.GET_SINGLE_LEAD_DATA)
	public ResponseEntity <Lead> getSingleLeadData(@RequestParam Long leadId)
	{
		Lead alllead= leadservice.getSingleLeadData(leadId);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}



//	@PutMapping("/{leadId}/status")
//	public ResponseEntity<String> updateLeadStatus(@PathVariable Long leadId,@RequestBody Status statusUpdateRequest)
//	{
//		boolean updated = leadservice.updateLeadsStatus(leadId, statusUpdateRequest);
//		if (updated) {
//			return ResponseEntity.ok("Lead status updated successfully.");
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lead or status not found.");
//		}
//	}
	
	
//	@PutMapping("/v1/lead/createEstimate")
	@PutMapping(UrlsMapping.CREATE_ESTIMATE)
	public ServiceDetails createEstimate(@RequestBody CreateServiceDetails createServiceDetails)
	{
		ServiceDetails res=leadservice.createEstimate(createServiceDetails);
//		return new ResponseEntity<>(updatedLeadData,HttpStatus.OK);
		 return res;
	}
//	@GetMapping("/v1/lead/getAllStatusHistory")
	@GetMapping(UrlsMapping.GET_ALL_STATUS_HISTORY)
	public ResponseEntity<List<LeadStatusChangeHistory>> getAllStatusHistory(@RequestParam Long leadId )
	{
	 try
	 {
		 List<LeadStatusChangeHistory> statusHistory = statusService.getStatusHistoryForLead(leadId);
		 return new ResponseEntity<>(statusHistory, HttpStatus.OK);
	 }
        catch (Exception e)
	{
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	}
	 }
	
	@PutMapping(UrlsMapping.UPDATE_ASSIGNEE)
	public Lead updateAssignee(@RequestParam Long leadId ,@RequestParam Long userId)
	{
		Lead res=leadservice.updateAssignee(leadId,userId);
//		return new ResponseEntity<>(updatedLeadData,HttpStatus.OK);
		 return res;
	}

}
