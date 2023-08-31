package com.lead.dashboard.service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LeadService {

    Lead createLead(LeadDTO leadDTO);
    List<Lead> getAllActiveCustomerLead();
    Lead updateLeadData(UpdateLeadDto updateLeadDto);
    boolean deleteLead(Long leadId);
	Lead getSingleLeadData(Long leadId);
	ServiceDetails createEstimate(CreateServiceDetails createServiceDetails);

//    boolean updateLeadsStatus(Long leadId, Status statusUpdateRequest);


}
