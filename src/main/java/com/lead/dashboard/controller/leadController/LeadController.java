package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.dto.AddProductInLead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.repository.UserRepo;
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
import java.util.Map;

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

	@Autowired
	UserRepo userRepo;


	@GetMapping(UrlsMapping.TEST)
	public String test()
	{
		//		return securityFeignClient.test();	
		return "Test";		 

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

	@GetMapping(UrlsMapping.GET_ALL_LEAD)
	public ResponseEntity <List<Lead>> getAllLead(@RequestParam Long userId,@RequestParam(required = false)Long statusId)
	{		
		//type->active , inActive 
		//status->new,potential . etc
		if(statusId==null) {
			List<Lead> alllead= leadservice.getAllActiveCustomerLead(userId);
			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}else {
			List<Lead> alllead= leadservice.getAllLead(userId,statusId);
			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}

	}

	@PutMapping(UrlsMapping.UPDATE_LEAD)
	public ResponseEntity<Lead> updateCustomerLeadData(@RequestBody UpdateLeadDto updateLeadDto)
	{
		System.out.println("Hit");
		List<String> roleList = userRepo.findRoleNameById(updateLeadDto.getUserId());
		System.out.println("ROLE  .. . . . . "+roleList);
		if(roleList.contains("ADMIN")|| roleList.contains("USER")) {
			System.out.println("In side .. ");
			Lead updatedLeadData = leadservice.updateLeadData(updateLeadDto);
			return new ResponseEntity<>(updatedLeadData,HttpStatus.OK);
		}else {
			//			Lead updatedLeadData = leadservice.updateLeadData(updateLeadDto);
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}

	}
	@PutMapping(UrlsMapping.UPDATE_LEAD_NAME)
	public ResponseEntity<Lead> updateLeadName(@RequestParam String leadName,@RequestParam Long leadId,@RequestParam(required=false) Long userId)
	{     
		Lead updatedLeadData = leadservice.updateLeadName(leadName,leadId,userId);
		return new ResponseEntity<>(updatedLeadData,HttpStatus.OK);
	}

	@DeleteMapping(UrlsMapping.DELETE_LEAD)
	public  ResponseEntity<Object>  deleteLead(@RequestParam Long leadId,@RequestParam Long userId)
	{
		List<String> roleList = userRepo.findRoleNameById(userId);
		if(roleList.contains("ADMIN")) {
			boolean  deletedLead = leadservice.deleteLead(leadId,userId);
			return new ResponseEntity<>(deletedLead==true?"lead has been deleted":"Not Deleted",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Access Denied",HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping(UrlsMapping.SEND_MAIL_IN_LEAD)
	public  boolean  sendMailInLead( String to, String subject, String text){
		//		emailServiceImpl.sendSimpleMessage(to,  subject,  text);
		return true;
	}

	@GetMapping(UrlsMapping.GET_SINGLE_LEAD_DATA)
	public ResponseEntity <Map<String,Object>> getSingleLeadData(@RequestParam Long leadId)
	{
		Map<String,Object> alllead= leadservice.getSingleLeadDataV2(leadId);
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


	@PutMapping(UrlsMapping.CREATE_ESTIMATE)
	public Lead createEstimate(@RequestBody CreateServiceDetails createServiceDetails)
	{
		Lead res=leadservice.createEstimate(createServiceDetails);
		return res;
	}
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
		return res;
	}

	@PutMapping(UrlsMapping.CREATE_PRODUCT_IN_LEAD)
	public  ResponseEntity<Lead> createProductInLead(@RequestBody AddProductInLead addProductInLead)
	{
		Lead res=null;
		try {
			res = leadservice.createProductInLead(addProductInLead);
		} catch (Exception e) {
			e.getMessage();
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), null);

		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PutMapping(UrlsMapping.DELETE_PRODUCT_IN_LEAD)
	public boolean deleteProductInLead(@RequestParam Long leadId,@RequestParam Long serviceId,@RequestParam(required = false) Long userId)
	{
		boolean res=leadservice.deleteProductInLead(leadId,serviceId,userId
				);
		return res;
	}
	
	@GetMapping(UrlsMapping.GET_ALL_DELETE_LEAD)
	public ResponseEntity <List<Lead>> getAllDeleteLead(@RequestParam Long userId)
	{		
		List<Lead> alllead= leadservice.getAllDeleteLead(userId);


		if(alllead!=null &&alllead.size()!=0) {
			return new ResponseEntity<>(alllead,HttpStatus.OK);
		
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

		}

	}
	
	
	@PutMapping(UrlsMapping.CREATE_VIEW_HISTORY)
	public Boolean viewHistory(@RequestParam Long userId,@RequestParam Long leadId)
	{		
		Boolean result= leadservice.viewHistory(userId,leadId);
		return result;

	}

}
