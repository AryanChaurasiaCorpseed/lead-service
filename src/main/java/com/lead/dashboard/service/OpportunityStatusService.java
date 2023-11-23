package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.opportunity.OpportunityStatus;

@Service
public interface OpportunityStatusService {

	OpportunityStatus createStatus(String name);

	List<OpportunityStatus> getAllStatuses();

	OpportunityStatus getStatusById(Long id);

	OpportunityStatus updateStatus(OpportunityStatus status);

	void deleteStatus(Long id);

}
