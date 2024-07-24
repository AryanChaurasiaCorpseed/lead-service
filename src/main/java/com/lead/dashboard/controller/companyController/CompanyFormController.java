package com.lead.dashboard.controller.companyController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.repository.CompanyFormRepo;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CompanyFormController {
	
	@Autowired
	CompanyFormRepo companyFormRepo;
	
	@Autowired
	LeadRepository leadRepository;
	
	
	@PostMapping(UrlsMapping.CREATE_COMPANY_FORM)
	public CompanyForm createCompanyForm(@RequestBody CreateFormDto createFormDto)
	{				
		CompanyForm companyForm =  new CompanyForm();
		companyForm.setAddress(createFormDto.getAddress());
		companyForm.setAssigneeId(createFormDto.getAssigneeId());
		companyForm.setCity(createFormDto.getCity());
		companyForm.setCompanyAge(createFormDto.getCompanyAge());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());
		companyForm.setCountry(createFormDto.getCountry());
		companyForm.setGstNo(createFormDto.getGstNo());
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setIsUnit(createFormDto.getIsUnit());
		Lead lead = leadRepository.findById(createFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(createFormDto.getPanNo());
		companyForm.setState(createFormDto.getState());
	    companyFormRepo.save(companyForm);
	   return companyForm;
	}
	@PostMapping(UrlsMapping.GET_ALL_COMPANY_FORM)
	public List<CompanyForm> getAllCompanyForm()
	{
		List<CompanyForm> compList = companyFormRepo.findAll();
		return compList;
	}

}
