package com.lead.dashboard.service;

import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.dto.CreateLeadStatus;
import com.lead.dashboard.dto.UpdateLeadStatusDto;
import com.lead.dashboard.domain.Status;

import java.util.List;

public interface StatusService {
    Status createStatus(CreateLeadStatus status);
    List<Status> getAllStatuses();
    Status getStatusById(Long id);
    void deleteStatus(Long id);

    void updateLeadStatus(Long leadId, Long statusId,Long userId);

    List<LeadStatusChangeHistory> getStatusHistoryForLead(Long leadId);
	List<Status> getAllPreviusStatus(Long statusId);
	Status updateStatus(UpdateLeadStatusDto updateLeadStatusDto);
}