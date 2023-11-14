package com.lead.dashboard.service;

import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.domain.Status;

import java.util.List;

public interface StatusService {
    Status createStatus(Status status);
    List<Status> getAllStatuses();
    Status getStatusById(Long id);
    Status updateStatus(Status status);
    void deleteStatus(Long id);

    void updateLeadStatus(Long leadId, Long statusId);

    List<LeadStatusChangeHistory> getStatusHistoryForLead(Long leadId);
}