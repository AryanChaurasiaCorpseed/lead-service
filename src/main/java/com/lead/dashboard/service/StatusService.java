package com.lead.dashboard.service;

import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.dto.CreateLeadStatus;
import com.lead.dashboard.domain.Status;

import java.util.List;

public interface StatusService {
    Status createStatus(CreateLeadStatus status);
    List<Status> getAllStatuses();
    Status getStatusById(Long id);
    Status updateStatus(Status status);
    void deleteStatus(Long id);

    void updateLeadStatus(Long leadId, String status);

    List<LeadStatusChangeHistory> getStatusHistoryForLead(Long leadId);
}