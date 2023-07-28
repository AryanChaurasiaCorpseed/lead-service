package com.lead.dashboard.service;

import com.lead.dashboard.domain.Status;

import java.util.List;

public interface StatusService {
    Status createStatus(Status status);
    List<Status> getAllStatuses();
    Status getStatusById(Long id);
    Status updateStatus(Status status);
    void deleteStatus(Long id);
}