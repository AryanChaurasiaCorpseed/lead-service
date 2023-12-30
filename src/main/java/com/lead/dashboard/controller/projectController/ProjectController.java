package com.lead.dashboard.controller.projectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.dto.UpdateProjectDto;
import com.lead.dashboard.service.ProjectService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;

    @PostMapping(UrlsMapping.CREATE_PROJECT)
    public ResponseEntity<List<Project>> createProjectbyEstimate(@RequestParam Long leadId,@RequestParam Long estimateId) {
    	List<Project> categoriesData = projectService.createProject(leadId,estimateId);
        if (categoriesData!=null) {
            return ResponseEntity.ok(categoriesData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(UrlsMapping.GET_ALL_PROJECT)
    public ResponseEntity<List<Project>> getAllProject() {
    	List<Project> projectList = projectService.getAllProject();
        if (projectList!=null) {
            return ResponseEntity.ok(projectList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(UrlsMapping.GET_PROJECT)
    public ResponseEntity<Project> getProject(@RequestParam Long projectId) {
         Project res = projectService.getProject(projectId);
        if (res!=null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping(UrlsMapping.UPDATE_PROJECT)
    public ResponseEntity<Project> UpdateProject(@RequestParam UpdateProjectDto UpdateProjectDto) {
         Project res = projectService.UpdateProject(UpdateProjectDto);
        if (res!=null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    
    
}
