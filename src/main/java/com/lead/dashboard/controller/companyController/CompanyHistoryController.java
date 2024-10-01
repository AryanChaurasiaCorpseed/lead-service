package com.lead.dashboard.controller.companyController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.service.CompanyHistoryService;
import com.lead.dashboard.serviceImpl.CompanyDataHistory;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CompanyHistoryController {


	
	@Autowired
	CompanyHistoryService companyHistoryService;
	
	
	@GetMapping(UrlsMapping.GET_ALL_COMPANY_HISTORY)
	public List<CompanyDataHistory> getAllCompanyHistory(@RequestParam Long companyId)
	{
		List<CompanyDataHistory> allCompany = companyHistoryService.getAllCompanyHistory(companyId);
		return allCompany;

	}
	
	
}
