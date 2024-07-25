package com.lead.dashboard.controller.companyController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	public List<Map<String,Object>> getAllCompany(@RequestParam Long userId)
	{
		List<Map<String,Object>> allCompany = companyService.getAllCompany(userId);
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
	
	
	

}
