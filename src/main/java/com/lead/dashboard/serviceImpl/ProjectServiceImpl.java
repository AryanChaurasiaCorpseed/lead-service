package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateProjectDto;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	LeadRepository leadRepository;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public List<Project> createProject(Long leadId,Long estimateId) {

		List<Project>p = new ArrayList<>();
		ServiceDetails estimate = serviceDetailsRepository.findById(estimateId).get();
		Optional<Lead> leadOp = leadRepository.findById(leadId);
		Lead lead =leadOp.get();
        Client client = estimate.getClient();
		long l = estimate.getQuantity();
		for(long i=0;i<l;i++) {
			Project project = new Project();
			project.setAssignee(lead.getAssignee());
			project.setCompany(estimate.getCompanies());
			project.setAmount(estimate.getProfessionalFees());
			project.setClient(client);
			project.setName(estimate.getName());
//			project.setProjectNo(null);  //no need to setup 
			project.setServiceDetails(estimate);
			project.setCreateDate(new Date());
			projectRepository.save(project);
			p.add(project);
		}
		return p;
	}

	@Override
	public List<Project> getAllProject() {	
		List<Project>projectList=projectRepository.findAll();
		return projectList;
	}

	@Override
	public Project getProject(Long projectId) {
		Optional<Project> projectOp = projectRepository.findById(projectId);
		Project p=null;
		if(projectOp!=null&& projectOp.isPresent()) {
			p=projectOp.get();
		}
		return p;
	}

	@Override
	public Project UpdateProject(UpdateProjectDto updateProjectDto) {
		Optional<Project> projectOp = projectRepository.findById(updateProjectDto.getProjectId());
		Project project =null;
		if(projectOp!=null && projectOp.get()!=null) {
			project=projectOp.get();
			if(!(project.getAssignee().getId().equals(updateProjectDto.getAssigneeId()))) {
				User assignee = userRepo.findById(updateProjectDto.getAssigneeId()).get();
				project.setAssignee(assignee);
			}
			project.setProjectNo(updateProjectDto.getProjectNo());
			project.setName(updateProjectDto.getName());
			project.setStatus(updateProjectDto.getStatus());
			if(!(updateProjectDto.getCompanyId().equals(project.getCompany().getId()))) {
				Company company = companyRepository.findById(updateProjectDto.getCompanyId()).get();
				project.setCompany(company);
			}
			projectRepository.save(project);
			
		}

		return project;
	}

}
