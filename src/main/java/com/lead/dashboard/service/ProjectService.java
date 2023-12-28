package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Project;

@Service
public interface ProjectService {

	Project createProject(Long leadId,Long estimateId);

	Project getAllProject();

}
