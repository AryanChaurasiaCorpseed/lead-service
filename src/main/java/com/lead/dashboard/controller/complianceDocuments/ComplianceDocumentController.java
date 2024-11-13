package com.lead.dashboard.controller.complianceDocuments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.dto.CreateCategoryDocDto;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.product.CategoryRepo;
import com.lead.dashboard.service.ComplianceDocService;
import com.lead.dashboard.util.UrlsMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	
	@PostMapping(UrlsMapping.CREATE_DOCUMENTS)
	public Boolean createDocumentInCategory(@RequestParam Long categoryId, @RequestParam String docList){
		Boolean res=complianceDocService.createDocumentInCategory(categoryId,docList);	
		return res;
	}
	
//	@GetMapping(UrlsMapping.GET_ALL_COMPLIANCE_DOCUMENTS)
	public List<Map<String,Object>>getAllComplianceDocumentsV2(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){
		List<Map<String,Object>>res=complianceDocService.getAllComplianceDocuments(page-1,size);	
		return res;
	}
	@GetMapping(UrlsMapping.GET_ALL_COMPLIANCE_DOCUMENTS_TOTAL_COUNT)
	public int getAllComplianceDocumentsTotalCounts(){
		int res=complianceDocService.getAllComplianceDocuments().size();	
		return res;
	}
	
	
} 
