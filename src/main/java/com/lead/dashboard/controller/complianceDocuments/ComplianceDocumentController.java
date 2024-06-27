package com.lead.dashboard.controller.complianceDocuments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.service.ComplianceDocService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ComplianceDocumentController {
 

	@Autowired
	CategoryRepo categoryRepo; 
	
	@Autowired
	ComplianceDocService complianceDocService;
	
	@GetMapping(UrlsMapping.GET_ALL_COMPLIANCE_DOCUMENTS)
	public List<Map<String,Object>>getAllComplianceDocuments(){
		List<Map<String,Object>>res=complianceDocService.getAllComplianceDocuments();	
		return res;
	}
} 
