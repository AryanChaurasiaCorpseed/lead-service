package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.service.OpportunitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeadOpportunity {


	@Autowired
	private OpportunitesService opportunitesService;

	@PostMapping("/save")
	public ResponseEntity<Opportunities> createOpportunity(@RequestBody Opportunities opportunity) throws CustomException {
		Opportunities createdOpportunity = opportunitesService.createOpportunity(opportunity);
		return new ResponseEntity<>(createdOpportunity, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Opportunities> updateOpportunity(@PathVariable Long id, @RequestBody Opportunities opportunityDetails) {
		Opportunities updatedOpportunity = opportunitesService.updateOpportunity(id, opportunityDetails);
		return ResponseEntity.ok(updatedOpportunity);
	}

	@GetMapping("/getData")
	public ResponseEntity<Opportunities> getOpportunity(@PathVariable Long id) {
		Opportunities opportunity = opportunitesService.getOpportunity(id);
		return ResponseEntity.ok(opportunity);
	}

	@GetMapping("/getAllData")
	public ResponseEntity<List<Opportunities>> getAllOpportunities() {
		List<Opportunities> opportunitiesList = opportunitesService.getAllOpportunities();
		return ResponseEntity.ok(opportunitiesList);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
		opportunitesService.deleteOpportunity(id);
		return ResponseEntity.noContent().build();
	}


}
