package com.lead.dashboard.service;

import com.lead.dashboard.dto.request.OpportunityRequest;
import com.lead.dashboard.dto.response.OpportunityResponse;
import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;

import java.util.List;

public interface OpportunitesService {
    OpportunityResponse createOpportunity(OpportunityRequest opportunityRequest) throws CustomException;

    OpportunityResponse updateOpportunity(Long id, OpportunityRequest opportunityRequest);

    List<Opportunities> getAllOpportunities();

    Opportunities getOpportunity(Long id);

    void deleteOpportunity(Long id);
}
