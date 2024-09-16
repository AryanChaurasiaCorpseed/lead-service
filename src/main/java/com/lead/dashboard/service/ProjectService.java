package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Project;

@Service
public interface ProjectService {

	Project createProject(Long leadId,Long estimateId);



	Project createProjectV2(Long leadId);

	List<Map<String, Object>> getAllProjectNameAndId();

	List<Map<String, Object>> getAllProject(Long userId, int page, int size);
}
