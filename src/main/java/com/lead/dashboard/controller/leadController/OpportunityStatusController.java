package com.lead.dashboard.controller.leadController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.opportunity.OpportunityStatus;
import com.lead.dashboard.dto.CreateLeadStatus;
import com.lead.dashboard.service.LeadService;
import com.lead.dashboard.service.OpportunityStatusService;

@RestController
@RequestMapping("/leadService/")
public class OpportunityStatusController {

	 @Autowired
	    private OpportunityStatusService opportunityStatusService;

	    @Autowired
	    private LeadService leadService;

	    @PostMapping("api/v1/OpportunityStatus/CreateOpportunityStatus")
	    public ResponseEntity<OpportunityStatus> createStatus(@RequestParam String name) {
	    	OpportunityStatus createdStatus = opportunityStatusService.createStatus(name);
	        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
	    }

	    @GetMapping("api/v1/OpportunityStatus/getAllStatus")
	    public ResponseEntity<List<OpportunityStatus>> getAllStatuses() {
	        List<OpportunityStatus> statuses = opportunityStatusService.getAllStatuses();
	        return new ResponseEntity<>(statuses, HttpStatus.OK);
	    }

	    @GetMapping("api/v1/OpportunityStatus/getStatusId")
	    public ResponseEntity<OpportunityStatus> getStatusById(@RequestParam("id") Long id) {
	    	OpportunityStatus status = opportunityStatusService.getStatusById(id);
	        return new ResponseEntity<>(status, HttpStatus.OK);
	    }


	    @PutMapping("api/v1/OpportunityStatus/updateStatus")
	    public ResponseEntity<OpportunityStatus> updateStatus(@RequestBody OpportunityStatus status) {
	    	OpportunityStatus updatedStatus = opportunityStatusService.updateStatus(status);
	        return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
	    }

	    @DeleteMapping("api/v1/OpportunityStatus/deleteStaus")
	    public ResponseEntity<OpportunityStatus> deleteStatus(@RequestParam("id") Long id) {
	        opportunityStatusService.deleteStatus(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
