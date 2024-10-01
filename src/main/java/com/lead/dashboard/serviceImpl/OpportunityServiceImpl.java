package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.exception.CustomException;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.opportunity.OpportunityStatus;
import com.lead.dashboard.dto.CreateOpportunity;
import com.lead.dashboard.dto.UpdateOpportunity;
import com.lead.dashboard.repository.OpportunitiesRepo;
import com.lead.dashboard.repository.OpportunityStatusRepo;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.OpportunitesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OpportunityServiceImpl implements OpportunitesService {

    @Autowired
    private OpportunitiesRepo opportunitiesRepo;
    
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    OpportunityStatusRepo opportunityStatusRepo;
    
    @Autowired
    ServiceDetailsRepository serviceDetailsRepository;
//    @Override
    public Opportunities createOpportunity(CreateOpportunity createOpportunity) throws CustomException {
         User user = null;
    	if(createOpportunity.getUserId()!=null) {
    		user=userRepo.findById(createOpportunity.getUserId()).get();
    	}

    	Opportunities opportunities =new Opportunities();
    	opportunities.setConfidence(createOpportunity.getConfidence());
    	opportunities.setDescription(createOpportunity.getDescription());
    	opportunities.setEstimateClose(createOpportunity.getEstimateClose());
    	opportunities.setTypePayment(createOpportunity.getTypePayment());
    	if(createOpportunity.getStatusId()!=null) {
    		OpportunityStatus status = opportunityStatusRepo.findById(createOpportunity.getStatusId()).get();
    		opportunities.setOpportunityStatus(status);
    	}
    	opportunities.setUser(user);
    	ServiceDetails service = serviceDetailsRepository.findById(createOpportunity.getServiceId()).get();
    	opportunitiesRepo.save(opportunities);
    	service.setOpportunities(opportunities);
    	serviceDetailsRepository.save(service);
    	return opportunities;
    }

    @Override
    public Opportunities updateOpportunity( UpdateOpportunity opportunityDetails) {
        Opportunities existingOpportunity = opportunitiesRepo.findById(opportunityDetails.getId())
                .orElseThrow(() -> new EntityNotFoundException("Opportunity not found with id: " + opportunityDetails.getId()
                ));

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
