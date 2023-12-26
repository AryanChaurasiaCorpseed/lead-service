package com.lead.dashboard.controller.dashboardController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.service.dashboardService.SalesDashboardService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class SalesDashboardController {
	
	
	@Autowired
	SalesDashboardService salesDashboardService;
	
	@GetMapping(UrlsMapping.GET_NO_OF_LEAD_DATA_GRAPH)
	public ResponseEntity <List<Lead>> getNoOfLeadDataGraph(@RequestParam Long userId,@RequestParam Long d1,@RequestParam Long d2)
	{
		List<Lead> alllead= salesDashboardService.getNoOfLeadDataGraph(userId,d1,d2);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
}
