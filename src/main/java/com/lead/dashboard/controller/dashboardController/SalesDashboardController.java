package com.lead.dashboard.controller.dashboardController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.GraphFilterDto;
import com.lead.dashboard.service.dashboardService.SalesDashboardService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class SalesDashboardController {
	
	
	@Autowired
	SalesDashboardService salesDashboardService;
	
	@GetMapping(UrlsMapping.GET_NO_OF_LEAD_DATA_GRAPH)
	public ResponseEntity <Map<String,Integer>> getNoOfLeadDataGraph(@RequestParam Long userId,@RequestParam Long d1,@RequestParam Long d2)
	{
		Map<String,Integer> alllead= salesDashboardService.getNoOfLeadDataGraph(userId,d1,d2);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
	
	@GetMapping(UrlsMapping.GET_LATEST_LEAD)
	public ResponseEntity <List<Lead>> getLatestLead(@RequestParam Long userId)
	{
		List<Lead> alllead= salesDashboardService.getLatestLead(userId);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
	// new amount wise
	
	@PostMapping(UrlsMapping.GET_ALL_PROJECT_GRAPH)
	public ResponseEntity <List<Map<String,Object>>> getAllProjectGraph(@RequestBody GraphFilterDto graphFilterDto)
	{
		List<Map<String,Object>> alllead= salesDashboardService.getAllProjectGraphV2(graphFilterDto);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
	
	@PostMapping(UrlsMapping.GET_ALL_PROJECT_GRAPH_AMOUNT)
	public ResponseEntity <List<Map<String,Object>>> getAllProjectGraphAmount(@RequestBody GraphFilterDto graphFilterDto)
	{
		List<Map<String,Object>> alllead= salesDashboardService.getAllProjectGraphAmountV2(graphFilterDto);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
	
	@PostMapping(UrlsMapping.GET_ALL_COMPANY_AMOUNT_GRAPH)
	public ResponseEntity <List<Map<String,Object>>> getAllCompanyAmountGraph(@RequestBody GraphFilterDto graphFilterDto)
	{
		List<Map<String,Object>> alllead= salesDashboardService.getAllCompanyAmountGraph(graphFilterDto);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
	
	@PostMapping(UrlsMapping.GET_ALL_AMOUNT_USER_WISE)
	public ResponseEntity <List<Map<String,Object>>> getAllAmountUserWise(@RequestBody GraphFilterDto graphFilterDto)
	{
		List<Map<String,Object>> alllead= salesDashboardService.getAllAmountUserWise(graphFilterDto);
		return new ResponseEntity<>(alllead,HttpStatus.OK);
	}
	
	
	
	
	
}
