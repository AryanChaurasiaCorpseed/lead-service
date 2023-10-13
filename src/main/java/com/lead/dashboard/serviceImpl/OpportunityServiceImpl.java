package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.dto.request.OpportunityRequest;
import com.lead.dashboard.dto.response.OpportunityResponse;
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
    public OpportunityResponse createOpportunity(OpportunityRequest opportunityRequest) throws CustomException {
        if (opportunityRequest == null || opportunityRequest.getEstimateClose() == null || opportunityRequest.getEstimateClose().isEmpty()) {
            throw new CustomException("Invalid input: Estimate close date is required.");
        }

        Opportunities opportunity = new Opportunities();
        opportunity.setEstimateClose(opportunityRequest.getEstimateClose());
        opportunity.setConfidence(opportunityRequest.getConfidence());
        opportunity.setValue(opportunityRequest.getValue());
        opportunity.setTypePayment(opportunityRequest.getTypePayment());
//        opportunity.setOpportunityStatus(opportunityRequest.getStatus());

        Opportunities savedOpportunity = opportunitiesRepo.save(opportunity);

        // Convert the saved entity to the response DTO
        OpportunityResponse response = new OpportunityResponse();
        response.setEstimateClose(savedOpportunity.getEstimateClose());
        response.setConfidence(savedOpportunity.getConfidence());
        response.setValue(savedOpportunity.getValue());
        response.setTypePayment(savedOpportunity.getTypePayment());
//        response.setStatus(savedOpportunity.getStatus());

        return response;
    }
    public OpportunityResponse updateOpportunity(Long id, OpportunityRequest opportunityRequest) {
        Opportunities existingOpportunity = opportunitiesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opportunity not found with id: " + id));

        existingOpportunity.setEstimateClose(opportunityRequest.getEstimateClose());
        existingOpportunity.setConfidence(opportunityRequest.getConfidence());
        existingOpportunity.setValue(opportunityRequest.getValue());
        existingOpportunity.setTypePayment(opportunityRequest.getTypePayment());

        Opportunities updatedOpportunity = opportunitiesRepo.save(existingOpportunity);

        // Convert and return as OpportunityResponse
        OpportunityResponse response = new OpportunityResponse();
        response.setEstimateClose(updatedOpportunity.getEstimateClose());
        response.setConfidence(updatedOpportunity.getConfidence());
        response.setValue(updatedOpportunity.getValue());
        response.setTypePayment(updatedOpportunity.getTypePayment());
//        response.setStatus(updatedOpportunity.getStatus());

        return response;
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
