package com.lead.dashboard.controller.companyController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lead.dashboard.domain.lead.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.UpdateCompanyDto;
import com.lead.dashboard.service.CompanyService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
//	@GetMapping(UrlsMapping.TEST)
//	public String test()
//	{
////		return securityFeignClient.test();	
//		return "Test";		 
//
//	}

	@PostMapping(UrlsMapping.CREATE_COMPANY)
	public ResponseEntity<Object> createCompany(@RequestBody CompanyDto companyDto)
	{				
		Company createdCompany;
		try {
			createdCompany = companyService.createCompany(companyDto);
			return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);		

		} catch (Exception e) {

			String msg = e.getMessage();
			return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);		

		}
	}
	
	
	@GetMapping(UrlsMapping.GET_COMPANY)
	public Company getCompany(@RequestParam Long companyId)
	{
		Company company = companyService.getCompany(companyId);
		return company;

	}
	@GetMapping(UrlsMapping.GET_ALL_COMPANY)
	public List<Map<String,Object>> getAllCompany(@RequestParam Long userId,@RequestParam(required = false) Long filterUserId,@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{
		List<Map<String,Object>> allCompany = companyService.getAllCompanyV2(userId,filterUserId,page-1,size);
		
		return allCompany;
 
	}
	
	@GetMapping(UrlsMapping.GET_ALL_PARENT_COMPANY)
	public List<Map<String,Object>> getAllParentCompany()
	{
		List<Map<String,Object>> allCompany = companyService.getAllParentCompany();
		return allCompany;

	}
	
	@GetMapping(UrlsMapping.GET_ALL_PROJECT_BY_COMPANY)
	public List<Map<String,Object>> getAllProjectByCompany(@RequestParam Long companyId)
	{
		List<Map<String,Object>> allProject = companyService.getAllProjectByCompany(companyId);
		return allProject;

	}
	
	@GetMapping(UrlsMapping.GET_ALL_LEAD_BY_COMPANY)
	public List<Map<String,Object>> getAllLeadByCompany(@RequestParam Long companyId)
	{
		List<Map<String,Object>> allLead = companyService.getAllLeadByCompany(companyId);
		return allLead;

	}

	@DeleteMapping(UrlsMapping.DELETE_COMPANY)
	public boolean deleteCompany(@RequestParam Long companyId)
	{
		boolean isDeleted = companyService.deleteCompany(companyId);

		return isDeleted;
	}
	
	@PutMapping(UrlsMapping.UPDATE_COMPANY)
	public boolean updateCompany(@RequestParam Long companyId)
	{
//		boolean isDeleted = companyService.updateCompany(companyId);

		return true;
	}     
	
	
	@GetMapping(UrlsMapping.GET_ALL_COMPANY_UNIT)
	public List<Map<String,Object>> getAllCompanyUnit(@RequestParam Long id)
	{
		List<Map<String,Object>> allCompany = companyService.getAllCompanyUnit(id);
		return allCompany;

	}
	
	
	@GetMapping(UrlsMapping.GET_COMPANY_BY_ID)
	public Map<String,Object> getCompanyById(@RequestParam Long id)
	{
		Map<String,Object> allCompany = companyService.getCompanyById(id);
		return allCompany;

	}
	
	
	
	
	
	@GetMapping(UrlsMapping.GET_COMPANY_BY_GST)
	public List<Company> getCompanyByGst(@RequestParam String gst)
	{
		List<Company> allCompany = companyService.getCompanyByGst(gst);
		return allCompany;

	}
	
	@PutMapping(UrlsMapping.UPDATE_COMPANY_ASSIGNEE)
	public boolean updateCompanyAssignee(@RequestParam Long companyId,@RequestParam Long assigneeId,@RequestParam Long currentUserId)
	{
//		boolean isDeleted = companyService.updateCompany(companyId);
		boolean res=companyService.updateCompanyAssignee(companyId,assigneeId,currentUserId);   
		return true;
	}

	@GetMapping(UrlsMapping.COMPANY_SEARCH)
	public ResponseEntity<List<Map<String, Object>>> searchCompanyByNameAndGST(
			@RequestParam String searchNameAndGSt, @RequestParam Long userId) {

		try {
			List<Map<String, Object>> companies = companyService.searchCompanyByNameAndGST(searchNameAndGSt, userId);
			if (companies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(UrlsMapping.UPDATE_MULTI_COMPANY_ASSIGNEE)
	public boolean updateMultiCompanyAssignee(@RequestBody UpdateCompanyDto updateCompanyDto)
	{
//		boolean isDeleted = companyService.updateCompany(companyId);
		boolean res=companyService.updateMultiCompanyAssignee(updateCompanyDto);   
		return true;
	}

	

	@PutMapping(UrlsMapping.UPDATE_MULTI_COMPANY_TEMP_ASSIGNEE)
	public boolean updateMultiCompanyTempAssignee(@RequestBody UpdateCompanyDto updateCompanyDto)
	{
//		boolean isDeleted = companyService.updateCompany(companyId);
		boolean res=companyService.updateMultiCompanyTempAssignee(updateCompanyDto);   
		return true;
	}

	@GetMapping(UrlsMapping.GET_ALL_TEMP_COMPANY)
	public List<Map<String,Object>> getAllTempCompany(@RequestParam Long userId,@RequestParam(required = false) Long filterUserId,@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{
		List<Map<String,Object>> allCompany = companyService.getAllTempCompany(userId,filterUserId,page-1,size);
		
		return allCompany;
 
	}
	
	@DeleteMapping(UrlsMapping.DELETE_TEMP_ASSIGNEE)
	public boolean deleteTempAssignee(@RequestBody UpdateCompanyDto updateCompanyDto)
	{
//		boolean isDeleted = companyService.updateCompany(companyId);
		boolean res=companyService.deleteTempAssignee(updateCompanyDto);   
		return true;
	}
	
	@GetMapping(UrlsMapping.GET_ALL_CONSULTANT_BY_COMPANY)
	public List<Map<String,Object>> getAllConsultantByCompany(@RequestParam Long userId,@RequestParam(required = false) Long filterUserId,@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{
		List<Map<String,Object>> allCompany = companyService.getAllConsultantByCompany(userId,page-1,size);
		
		return allCompany;
 
	}
	
	
	
	@GetMapping(UrlsMapping.GET_ALL_CONSULTANT_BY_COMPANY_COUNT)
	public int getAllConsultantByCompanyCount(@RequestParam Long userId)
	{
		int allCompany = companyService.getAllConsultantByCompanyCount(userId);
		
		return allCompany;
 
	}
	





	@GetMapping(UrlsMapping.COMPANY_SEARCH_BY_GST_AND_CONTACT_DETAILS)
	public ResponseEntity<List<Map<String, Object>>> companySearchByGstAndContactDetails(
			@RequestParam String searchNameAndGSt, @RequestParam Long userId,@RequestParam String fieldSearch) {

		try {
			List<Map<String, Object>> companies = companyService.companySearchByGstAndContactDetails(searchNameAndGSt, userId,fieldSearch);
			if (companies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(companies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
