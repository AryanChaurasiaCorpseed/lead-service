package com.lead.dashboard.controller.OrganizationController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.Organization.Plans;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.CreatePlans;
import com.lead.dashboard.dto.UpdatePlans;
import com.lead.dashboard.service.PlansService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class PlansController {
	
	@Autowired
	PlansService plansService;
	
	@PostMapping(UrlsMapping.CREATE_PLANS)
	
	public ResponseEntity<Plans> createPlans(@RequestBody CreatePlans createPlans)
	{				
		Plans res = plansService.createPlans(createPlans);

		if (res!=null) {
				return new ResponseEntity<>(res, HttpStatus.CREATED);		
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@GetMapping(UrlsMapping.GET_PLANS)
	public Plans getPlans(@RequestParam Long plansId)
	{
		Plans res = plansService.getPlans(plansId);
		return res;

	}
	@GetMapping(UrlsMapping.GET_ALL_PLANS)
	public List<Plans> getAllPlans()
	{
		List<Plans> res = plansService.getAllPlans();
		return res;

	}

	@DeleteMapping(UrlsMapping.DELETE_PLANS)
	public boolean deletePlans(@RequestParam Long plansId,@RequestParam Long userId)
	{
		boolean isDeleted = plansService.deletePlans(plansId,userId);

		return isDeleted;
	}
	
	@PutMapping(UrlsMapping.UPDATE_PLANS)
	public Plans updatePlans(@RequestBody UpdatePlans updatePlans)
	{
		Plans res = plansService.updatePlans(updatePlans);

		return res;
	}


}
