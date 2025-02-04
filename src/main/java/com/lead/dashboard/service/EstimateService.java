package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

	Map<String,Object> getEstimateByLeadId(Long leadId);

	List<Map<String,Object>> getEstimateByUserId(Long userId, int page, int size);

	long getEstimateByUserIdCount(Long userId);

	Map<String, Object> getEstimateById(Long estimateId);

	List<Map<String, Object>> getEstimateByStatus(String status,int page,int size,Long userId);

	Boolean approvedEstimate(String status,Long estimateId,Long userId);

	long getEstimateByStatusCount(String status, Long userId);

}
