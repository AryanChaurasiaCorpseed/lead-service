package com.lead.dashboard.service;

import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;

import java.util.List;

public interface OpportunitesService {
    Opportunities createOpportunity(Opportunities opportunity) throws CustomException;

    Opportunities updateOpportunity(Long id, Opportunities opportunityDetails);

    List<Opportunities> getAllOpportunities();

    Opportunities getOpportunity(Long id);

    void deleteOpportunity(Long id);
}
