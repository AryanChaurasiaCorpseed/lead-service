package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.repository.OpportunitiesRepo;
import com.lead.dashboard.service.OpportunitesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpportunityServiceImpl implements OpportunitesService {

    @Autowired
    private OpportunitiesRepo opportunitiesRepo;
    @Override
    public Opportunities createOpportunity(Opportunities opportunity) throws CustomException {

        if (opportunity == null || opportunity.getEstimateClose() == null || opportunity.getEstimateClose().isEmpty()) {
            throw new CustomException("Invalid input: Estimate close date is required.");
        }
        return opportunitiesRepo.save(opportunity);
    }

    @Override
    public Opportunities updateOpportunity(Long id, Opportunities opportunityDetails) {
        Opportunities existingOpportunity = opportunitiesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opportunity not found with id: " + id));

        existingOpportunity.setEstimateClose(opportunityDetails.getEstimateClose());
        existingOpportunity.setConfidence(opportunityDetails.getConfidence());
        existingOpportunity.setValue(opportunityDetails.getValue());
        existingOpportunity.setTypePayment(opportunityDetails.getTypePayment());

        // Save and return the updated opportunity
        return opportunitiesRepo.save(existingOpportunity);
    }

    @Override
    public Opportunities getOpportunity(Long id) {
        return opportunitiesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opportunity not found with id: " + id));
    }

    @Override
    public List<Opportunities> getAllOpportunities() {
        return opportunitiesRepo.findAll();
    }


    @Override
    public void deleteOpportunity(Long id) {
        Opportunities opportunity = opportunitiesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opportunity not found with id: " + id));
        opportunitiesRepo.delete(opportunity);
    }
}
