package com.lead.dashboard.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Project;

@Service
public interface ProjectService {

	Project createProject(Long leadId,Long estimateId);

	Map<String,Object> getAllProject();

	Project createProjectV2(Long leadId);

}
