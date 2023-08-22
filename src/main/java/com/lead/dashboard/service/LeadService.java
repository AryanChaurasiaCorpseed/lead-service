package com.lead.dashboard.service;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;

import java.util.List;


public interface LeadService {

    Lead createLead(LeadDTO leadDTO);
    List<Lead> getAllActiveCustomerLead();
    Lead updateLeadData(UpdateLeadDto updateLeadDto);
    boolean deleteLead(Long leadId);
	Lead getSingleLeadData(Long leadId);

//    boolean updateLeadsStatus(Long leadId, Status statusUpdateRequest);


}
