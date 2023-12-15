package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.lead.dashboard.domain.Organization.Plans;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreatePlans;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdatePlans;
import com.lead.dashboard.repository.PlansRepository;
import com.lead.dashboard.service.PlansService;
import com.lead.dashboard.util.UrlsMapping;

public class PlansServiceImpl implements PlansService{
	
	@Autowired
	PlansRepository plansRepository;

	@Override
	public Plans createPlans(CreatePlans createPlans) {
		// TODO Auto-generated method stub
		Plans plans = new Plans();
		plans.setName(createPlans.getName());
		plans.setDuration(createPlans.getDuration());
		plans.setPrice(createPlans.getPrice());
		plansRepository.save(plans);
		return plans;
	}

	@Override
	public boolean deletePlans(Long plansId, Long userId) {
		// TODO Auto-generated method stub
		   boolean flag=false;
		   Plans plans = plansRepository.findById(userId).get();
		   plans.setDeleted(true);
		   plansRepository.save(plans);
		   flag=true;
		return flag;
	}

	@Override
	public Plans updatePlans(UpdatePlans updatePlans) {
		 Plans plans = plansRepository.findById(updatePlans.getId()).get();
		 plans.setName(updatePlans.getName());
		 plans.setPrice(updatePlans.getPrice());
         plansRepository.save(plans);
		return plans;
	}

	@Override
	public Plans getPlans(Long id) {
		 Plans plans = plansRepository.findById(id).get();
		return plans;
	}

	@Override
	public List<Plans> getAllPlans() {
		  List<Plans> plansList = plansRepository.findAll();
		return plansList;
	}
	
	
	

}
