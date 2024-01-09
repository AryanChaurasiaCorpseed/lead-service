package com.lead.dashboard.service;

import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.dto.AddProductInLead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;

import java.util.List;
import java.util.Map;


public interface LeadService {

    Lead createLead(LeadDTO leadDTO);
    Lead updateLeadData(UpdateLeadDto updateLeadDto);
	Lead getSingleLeadData(Long leadId);
	Lead createEstimate(CreateServiceDetails createServiceDetails);
	List<Lead> getAllActiveCustomerLead(Long uId);
	Lead updateAssignee(Long leadId, Long userId);
	Lead createProductInLead(AddProductInLead addProductInLead) throws Exception;
	Lead updateLeadName(String leadName, Long leadId, Long userId);
	boolean deleteProductInLead(Long leadId,Long productId,Long userId);
	Map<String, Object> getSingleLeadDataV2(Long leadId);
	boolean deleteLead(Long leadId, Long userId);
	List<ServiceDetails> getAllEstimate();
	ServiceDetails getEstimate(Long estimateId);
	List<Lead> getAllLead(Long userId, Long statusId);
	List<Lead> getAllDeleteLead(Long uId);




}
