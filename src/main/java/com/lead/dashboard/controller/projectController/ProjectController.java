package com.lead.dashboard.controller.projectController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.service.ProjectService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;

    @PostMapping(UrlsMapping.CREATE_PROJECT)
    public ResponseEntity<Project> createProjectbyEstimate(@RequestParam Long leadId,@RequestParam Long estimateId) {
    	Project categoriesData = projectService.createProject(leadId,estimateId);
        if (categoriesData!=null) {
            return ResponseEntity.ok(categoriesData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping(UrlsMapping.CREATE_PROJECT_V2)
    public ResponseEntity<Project> createProjectbyEstimateV2(@RequestParam Long leadId) {
    	Project categoriesData = projectService.createProjectV2(leadId);
        if (categoriesData!=null) {
            return ResponseEntity.ok(categoriesData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PostMapping(UrlsMapping.GET_ALL_PROJECT)
    public ResponseEntity<Map<String,Object>> getAllProject() {
    	Map<String,Object> pMap = projectService.getAllProject();
        if (pMap!=null) {
            return ResponseEntity.ok(pMap);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
