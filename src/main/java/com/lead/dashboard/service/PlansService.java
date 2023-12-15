package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Organization.Plans;
import com.lead.dashboard.dto.CreatePlans;
import com.lead.dashboard.dto.UpdatePlans;
@Service
public interface PlansService {


	Plans createPlans(CreatePlans createPlans);

	boolean deletePlans(Long plansId, Long userId);

	Plans updatePlans(UpdatePlans updatePlans);

	Plans getPlans(Long id);

	List<Plans> getAllPlans();

}
