package com.lead.dashboard.service;

import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.dto.CreateOpportunity;
import com.lead.dashboard.dto.UpdateOpportunity;

import java.util.List;

public interface OpportunitesService {
    Opportunities createOpportunity(CreateOpportunity createOpportunity) throws CustomException;

    Opportunities updateOpportunity(UpdateOpportunity opportunityDetails);

    List<Opportunities> getAllOpportunities();

    Opportunities getOpportunity(Long id);

    void deleteOpportunity(Long id);
}
