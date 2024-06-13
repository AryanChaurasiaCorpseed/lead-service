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
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ComplianceDocumentController {
 

	@Autowired
	ServiceDetailsRepository serviceDetailsRepository; 
	
	@GetMapping(UrlsMapping.GET_ALL_COMPLIANCE_DOCUMENTS)
	public List<Map<String,Object>>getAllComplianceDocuments(){
		List<ServiceDetails> serviceDetail = serviceDetailsRepository.findAll();
		List<Map<String,Object>> res = new ArrayList<>();
		for(ServiceDetails service:serviceDetail) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", service.getId());
			map.put("name", service.getName());
			map.put("documents", service.getName());
			res.add(map);
		}
		return res;
	}
} 
