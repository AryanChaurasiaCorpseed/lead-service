package com.lead.dashboard.service;

import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;

import java.util.List;


public interface LeadService {

    Lead createLead(LeadDTO leadDTO);
    Lead updateLeadData(UpdateLeadDto updateLeadDto);
    boolean deleteLead(Long leadId);
	Lead getSingleLeadData(Long leadId);
	ServiceDetails createEstimate(CreateServiceDetails createServiceDetails);
	List<Lead> getAllActiveCustomerLead(Long uId);




}
