package com.lead.dashboard.service;

import com.lead.dashboard.controller.leadController.UpdateLeadOriginal;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.dto.AddProductInLead;
import com.lead.dashboard.dto.AllLeadFilter;
import com.lead.dashboard.dto.ChildLeadDto;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.DeleteMultiLeadDto;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.dto.UpdateMultiLeadAssignee;

import java.util.List;
import java.util.Map;


public interface LeadService {

    Lead createLead(LeadDTO leadDTO);
    Lead updateLeadData(UpdateLeadDto updateLeadDto);
	Lead getSingleLeadData(Long leadId);
	Lead createEstimate(CreateServiceDetails createServiceDetails);
	List<Lead> getAllActiveCustomerLead(AllLeadFilter allLeadFilter);
	Lead updateAssignee(Long leadId, Long userId, Long updatedById);
	Lead createProductInLead(AddProductInLead addProductInLead) throws Exception;
	Lead updateLeadName(String leadName, Long leadId, Long userId);
	boolean deleteProductInLead(Long leadId,Long productId,Long userId);
	Map<String, Object> getSingleLeadDataV2(Long leadId,Long currentUserId);
	boolean deleteLead(Long leadId, Long userId);
	List<ServiceDetails> getAllEstimate();
	ServiceDetails getEstimate(Long estimateId);
	List<Lead> getAllLead(AllLeadFilter allLeadFilter);
	List<Lead> getAllDeleteLead(Long uId);
	Boolean viewHistory(Long userId, Long leadId);
	Boolean updateMultiLeadAssigne(UpdateMultiLeadAssignee updateMultiLeadAssignee);
	Boolean deleteMultiLead(DeleteMultiLeadDto deleteMultiLeadDto);
	Lead createLeadV2(LeadDTO leadDTO);
	Boolean updateStatusAndAutoSame(Long leadId, Long updatedById, boolean isAutoSame, String status);
	Boolean updateLeadOriginalName(UpdateLeadOriginal updateLeadOriginal);
	List<Map<String, Object>> getAllLeadNameAndId();
	Boolean updateHelper(Long userId, Long leadId);
	Boolean leadAssignSamePerson(Long leadId);


	Lead createLeadViaSheet(LeadDTO leadDTO);
	List<Lead> getAllLeadV3(AllLeadFilter allLeadFilter, int page, int size);
	List<Lead> getAllActiveCustomerLeadV3(AllLeadFilter allLeadFilter, int page, int size);
	Boolean updateLeadDescription(Long leadId, String desc);
	List<Lead> searchLeads(String searchParam, Long userId);
	Lead createLeadV2New(LeadDTO leadDTO);
	Integer getAllLeadCount(AllLeadFilter allLeadFilter);
	Integer getAllActiveCustomerLeadCount(AllLeadFilter allLeadFilter);
	Boolean addChildLead(ChildLeadDto childLeadDto);
	Boolean addReopenByQuality(Long currentUerId, Long leadId, boolean isMarked);
}
