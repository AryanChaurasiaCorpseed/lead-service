package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.dto.CreateOpportunity;
import com.lead.dashboard.dto.UpdateOpportunity;
import com.lead.dashboard.service.OpportunitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lead.dashboard.util.UrlsMapping;

import java.util.List;

@RestController
public class LeadOpportunity {


	@Autowired
	private OpportunitesService opportunitesService;

	@PostMapping(UrlsMapping.CREATE_OPPORTUNITY)
	public ResponseEntity<Opportunities> createOpportunity(@RequestBody CreateOpportunity createOpportunity) throws CustomException {
		Opportunities createdOpportunity = opportunitesService.createOpportunity(createOpportunity);
		return new ResponseEntity<>(createdOpportunity, HttpStatus.CREATED);
	}

	@PutMapping(UrlsMapping.UPDATE_OPPORTUNITY)
	public ResponseEntity<Opportunities> updateOpportunity(@RequestBody UpdateOpportunity opportunityDetails) {
		Opportunities updatedOpportunity = opportunitesService.updateOpportunity(opportunityDetails);
		return ResponseEntity.ok(updatedOpportunity);
	}

	@GetMapping(UrlsMapping.GET_OPPORTUNITY_BY_ID)
	public ResponseEntity<Opportunities> getOpportunityById(@PathVariable Long id) {
		Opportunities opportunity = opportunitesService.getOpportunity(id);
		return ResponseEntity.ok(opportunity);
	}

	@GetMapping(UrlsMapping.GET_ALL_OPPORTUNITY)
	public ResponseEntity<List<Opportunities>> getAllOpportunities() {
		List<Opportunities> opportunitiesList = opportunitesService.getAllOpportunities();
		return ResponseEntity.ok(opportunitiesList);
	}

	@DeleteMapping(UrlsMapping.DELETE_OPPORTUNITY)
	public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
		opportunitesService.deleteOpportunity(id);
		return ResponseEntity.noContent().build();
	}


}
