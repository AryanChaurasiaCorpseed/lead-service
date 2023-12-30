package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Project;
import com.lead.dashboard.dto.UpdateProjectDto;

@Service
public interface ProjectService {

	List<Project> createProject(Long leadId,Long estimateId);

	List<Project> getAllProject();


	Project getProject(Long projectId);

	Project UpdateProject(UpdateProjectDto updateProjectDto);

}
