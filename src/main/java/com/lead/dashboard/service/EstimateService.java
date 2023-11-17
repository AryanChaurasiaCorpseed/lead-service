package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateServiceDetails;

@Service
public interface EstimateService {

	Lead createEstimate(CreateServiceDetails createServiceDetails);

	List<ServiceDetails> getAllEstimate();

	ServiceDetails getEstimate(Long estimateId);

}
