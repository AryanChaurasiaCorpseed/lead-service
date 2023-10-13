package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.dto.request.OpportunityRequest;
import com.lead.dashboard.dto.response.OpportunityResponse;
import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.service.OpportunitesService;
import com.lead.dashboard.util.UrlsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeadOpportunity {


	@Autowired
	private OpportunitesService opportunitiesService;

//	@PostMapping("/save")
	@PostMapping(UrlsMapping.CREATE_OPPORTUNITY)
	public ResponseEntity<OpportunityResponse> createOpportunity(@RequestBody OpportunityRequest opportunityRequest) throws CustomException {
		OpportunityResponse createdOpportunityResponse = opportunitiesService.createOpportunity(opportunityRequest);
		return new ResponseEntity<>(createdOpportunityResponse, HttpStatus.CREATED);
	}

	@PutMapping(UrlsMapping.UPDATE_OPPORTUNITY)
	public ResponseEntity<OpportunityResponse> updateOpportunity(@PathVariable Long id,@RequestBody OpportunityRequest opportunityRequest) {
		OpportunityResponse updatedOpportunity = opportunitiesService.updateOpportunity(id, opportunityRequest);
		return ResponseEntity.ok(updatedOpportunity);
	}

	@GetMapping(UrlsMapping.GET_OPPORTUNITY)
	public ResponseEntity<Opportunities> getOpportunity(@PathVariable Long id) {
		Opportunities opportunity = opportunitiesService.getOpportunity(id);
		return ResponseEntity.ok(opportunity);
	}

	@GetMapping(UrlsMapping.GET_ALL_OPPORTUNITY)
	public ResponseEntity<List<Opportunities>> getAllOpportunities() {
		List<Opportunities> opportunitiesList = opportunitiesService.getAllOpportunities();
		return ResponseEntity.ok(opportunitiesList);
	}

	@DeleteMapping(UrlsMapping.DELETE_OPPORTUNITY)
	public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
		opportunitiesService.deleteOpportunity(id);
		return ResponseEntity.noContent().build();
	}

}
