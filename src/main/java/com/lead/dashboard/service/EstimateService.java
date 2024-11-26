package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.EditEstimate;
import com.lead.dashboard.dto.EditEstimateAddress;

@Service
public interface EstimateService {

	Boolean createEstimate(CreateServiceDetails createServiceDetails) throws Exception;

	List<ServiceDetails> getAllEstimate();

	ServiceDetails getEstimate(Long estimateId);

	Boolean editEstimateInvoice(EditEstimate editEstimate);

	ServiceDetails editEstimateAddress(EditEstimateAddress editEstimateAddress);

	ServiceDetails getEstimateByLeadId(Long leadId);

	List<ServiceDetails> getEstimateByUserId(Long userId, int page, int size);

	long getEstimateByUserIdCount(Long userId);

}
