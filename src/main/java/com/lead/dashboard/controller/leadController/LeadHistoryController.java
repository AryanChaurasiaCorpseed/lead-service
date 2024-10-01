package com.lead.dashboard.controller.leadController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.LeadHistory;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.service.LeadHistorySevice;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class LeadHistoryController {

	@Autowired
	LeadHistorySevice leadHistorySevice;
	
	@GetMapping(UrlsMapping.GET_ALL_LEAD_HISTORY)
	public ArrayList<Map<String,Object>> getAllLeadHistory(Long leadId)
	{
		ArrayList<Map<String,Object>> alllead= leadHistorySevice.getAllLeadHistory(leadId);
		return alllead;
	}
}
