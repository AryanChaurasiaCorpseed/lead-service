package com.lead.dashboard.controller.OrganizationController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Organization.Plans;
import com.lead.dashboard.dto.CreatePlans;
import com.lead.dashboard.util.UrlsMapping;
import com.lead.dashboard.service.PreOrganizationService;

@RestController
public class PreOrganizationController {
	@Autowired
	PreOrganizationService PreOrganizationService;
	
	@PostMapping(UrlsMapping.CREATE_PRE_ORGANIZATION_STATUS)	
	public ResponseEntity<?> createPreOrganizationStatus(@RequestParam Long organizationId)
	{				
		boolean res = PreOrganizationService.createPreOrganizationStatus(organizationId);

		if (res) {
				return new ResponseEntity<>(res, HttpStatus.CREATED);		
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
    

}
