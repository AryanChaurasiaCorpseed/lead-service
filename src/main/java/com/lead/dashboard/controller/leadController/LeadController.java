package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.dto.AddProductInLead;
import com.lead.dashboard.dto.AllLeadFilter;
import com.lead.dashboard.dto.AutoAssignDto;
import com.lead.dashboard.dto.ChildLeadDto;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.DeleteMultiLeadDto;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.dto.UpdateMultiLeadAssignee;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.StatusService;
import com.lead.dashboard.util.UrlsMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lead.dashboard.config.EmailServiceImpl;
import com.lead.dashboard.config.SecurityFeignClient;
import com.lead.dashboard.controller.inboxController.LeadCrone;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
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
	LeadCrone leadCrone;

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
				User user = userRepo.findByUserIdAndIsDeletedFalse(leadDTO.getCreatedById());
				Lead createdLead = leadservice.createLeadV2New(leadDTO);
				return new ResponseEntity<>(createdLead, HttpStatus.CREATED);

//				if(user!=null) {
//					Lead createdLead = leadservice.createLeadV2New(leadDTO);
//					return new ResponseEntity<>(createdLead, HttpStatus.CREATED);
//
//				}else {
//					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//				}
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create lead", e);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping(UrlsMapping.GET_ALL_LEAD_COUNT)
	public ResponseEntity <	Long> getAllLeadCount(@RequestBody AllLeadFilter allLeadFilter)
	{		
		//type->active , inActive 
		//status->new,potential . etc
		if(allLeadFilter.getStatusId()!=null && allLeadFilter.getStatusId().size()!=0) {
			long alllead= leadservice.getAllLeadCountV2(allLeadFilter);
			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}else {
			long alllead= leadservice.getAllActiveCustomerLeadCountV2(allLeadFilter);

			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}

	}
//	
//	@PostMapping(UrlsMapping.GET_ALL_LEAD)
//	public ResponseEntity <List<Lead>> getAllLead(@RequestBody AllLeadFilter allLeadFilter)
//	{		
//		//type->active , inActive 
//		//status->new,potential . etc
//		if(allLeadFilter.getStatusId()!=null && allLeadFilter.getStatusId().size()!=0) {
//			List<Lead> alllead= leadservice.getAllLead(allLeadFilter);
//			return new ResponseEntity<>(alllead,HttpStatus.OK);
//		}else {
//			List<Lead> alllead= leadservice.getAllActiveCustomerLead(allLeadFilter);
//
//			return new ResponseEntity<>(alllead,HttpStatus.OK);
//		}
//
//	}
	
	
	@PostMapping(UrlsMapping.GET_ALL_LEAD)
	public ResponseEntity <List<Lead>> getAllLeadV3(@RequestBody AllLeadFilter allLeadFilter,@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{		
		//type->active , inActive 
		//status->new,potential . etc
		if(allLeadFilter.getStatusId()!=null && allLeadFilter.getStatusId().size()!=0) {
			List<Lead> alllead= leadservice.getAllLeadV3(allLeadFilter,page-1,size);
			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}else {
			List<Lead> alllead= leadservice.getAllActiveCustomerLeadV3(allLeadFilter,page-1,size);

			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}

	}
	
	
	@PostMapping(UrlsMapping.GET_ALL_LEAD_FOR_IMPORT)
	public ResponseEntity <List<Map<String,Object>>> getAllLeadForImport(@RequestBody AllLeadFilter allLeadFilter,@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{		
		//type->active , inActive 
		//status->new,potential . etc
		if(allLeadFilter.getStatusId()!=null && allLeadFilter.getStatusId().size()!=0) {
			List<Map<String,Object>> alllead= leadservice.getAllLeadForImport(allLeadFilter,page-1,size);
			return new ResponseEntity<>(alllead,HttpStatus.OK);
		}else {
			List<Map<String,Object>> alllead= leadservice.getAllActiveCustomerLeadForExport(allLeadFilter,page-1,size);

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
	public ResponseEntity <Map<String,Object>> getSingleLeadData(@RequestParam Long leadId,@RequestParam(required=false) Long currentUserId)
	{
		Map<String,Object> alllead= leadservice.getSingleLeadDataV2(leadId,currentUserId);
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
	public Lead updateAssignee(@RequestParam Long leadId ,@RequestParam Long userId,@RequestParam(required=false) Long updatedById)
	{
		Lead res=leadservice.updateAssignee(leadId,userId,updatedById);
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
		boolean res=leadservice.deleteProductInLead(leadId,serviceId,userId);
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
	@PutMapping(UrlsMapping.UPDATE_MULTI_LEAD_ASSIGNE)
	public Boolean updateMultiLeadAssigne(@RequestBody UpdateMultiLeadAssignee updateMultiLeadAssignee)
	{		
		Boolean result= leadservice.updateMultiLeadAssigne(updateMultiLeadAssignee);
		return result;

	}
	
	 
	@DeleteMapping(UrlsMapping.DELETE_MULTI_LEAD)
	public Boolean deleteMultiLead(@RequestBody DeleteMultiLeadDto DeleteMultiLeadDto)
	{		
		Boolean result= leadservice.deleteMultiLead(DeleteMultiLeadDto);
		return result;

	}
	
	@GetMapping(UrlsMapping.CRONE_HIT)
	public void assignLeadByCrone()
	{		
		System.out.println("Lead crone 12 ");
		 leadCrone.assignLeadByCrone();

	}


	@PutMapping(UrlsMapping.UPDATE_STATUS_AND_AUTO_SAME)
	public Boolean updateStatusAndAutoSame(@RequestBody AutoAssignDto autoAssignDto)
	{ 
		Long leadId=autoAssignDto.getLeadId();
		Long updatedById=autoAssignDto.getUpdatedById();
		boolean isAutoSame=autoAssignDto.isAutoSame();
		
		Boolean res=leadservice.updateStatusAndAutoSame(leadId,updatedById,isAutoSame,autoAssignDto.getStatus());
		return res;
	}

	@PutMapping(UrlsMapping.UPDATE_LEAD_ORIGINAL_NAME)
	public Boolean updateLeadOriginalName(@RequestBody UpdateLeadOriginal updateLeadOriginal)
	{ 
		
		Boolean res=leadservice.updateLeadOriginalName(updateLeadOriginal);
		return res;
	}
	@GetMapping(UrlsMapping.GET_ALL_LEAD_NAME_AND_ID)
	public List<Map<String,Object>> getAllLeadNameAndId()
	{ 
		
		List<Map<String,Object>> res=leadservice.getAllLeadNameAndId();
		return res;
	}
	
	@PutMapping(UrlsMapping.UPDATE_HELPER)
	public ResponseEntity <Boolean> updateHelper(@RequestParam(required=false) Long userId,@RequestParam Long leadId)
	{		
		Boolean res= leadservice.updateHelper(userId,leadId);


		if(res) {
			return new ResponseEntity<>(res,HttpStatus.OK);
		
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

		}

	}
	
	@PutMapping(UrlsMapping.LEAD_ASSIGN_SAME_PERSON)
	public Boolean leadAssignSamePerson(@RequestParam Long leadId)
	{ 
		
		Boolean res=leadservice.leadAssignSamePerson(leadId);
		return res;
	}

	@PostMapping("/createLeadByHelper")
	public ResponseEntity<Lead> createByHelper(@RequestBody LeadDTO leadDTO)
	{
		if (leadDTO!=null) {
			try {
				Lead createdLead = leadservice.createLeadViaSheet(leadDTO);
				return new ResponseEntity<>(createdLead, HttpStatus.CREATED);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create lead", e);
			}
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping(UrlsMapping.UPDATE_LEAD_DESCRIPTION)
	public Boolean updateLeadDescription(@RequestParam Long leadId,@RequestParam String desc)
	{ 
		
		Boolean res=leadservice.updateLeadDescription(leadId,desc);
		return res;
	}
	
	@GetMapping(UrlsMapping.LEAD_SEARCH)
	public ResponseEntity<List<Lead>> searchLeads(@RequestParam String searchParam,@RequestParam Long userId) {

		try {
			List<Lead> leads = leadservice.searchLeads(searchParam,userId);
			if (leads.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(leads, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(UrlsMapping.ADD_CHILD_LEAD)
	public Boolean addChildLead(@RequestBody ChildLeadDto childLeadDto)
	{ 
		
		Boolean res=leadservice.addChildLead(childLeadDto);
		return res;
	}
	
	@PutMapping(UrlsMapping.ADD_REOPEN_BY_QUALITY)
	public Boolean addReopenByQuality(@RequestParam Long currentUerId,@RequestParam Long leadId , @RequestParam boolean isMarked )
	{ 
		
		Boolean res=leadservice.addReopenByQuality(currentUerId,leadId,isMarked);
		return res;
	}
	
	@PutMapping(UrlsMapping.CALCULATE_BASE_AMOUNT)
	public double calculateBaseAmount(@RequestParam double totalAmount,@RequestParam double gstPercent )
	{ 
		double totalPercent = 100+gstPercent;
		double baseAmont=totalAmount/totalPercent;
		double res = baseAmont*100;
		return res;
	}
	
	@PutMapping(UrlsMapping.AUTO_ON_OFF)
	public Boolean autoOnOff(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{ 
		Boolean res=leadservice.autoOnOff(page,size);
		
		return  res;

	}

}
