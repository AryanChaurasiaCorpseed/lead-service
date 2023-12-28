package com.lead.dashboard.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;
	
	@Autowired
	LeadRepository leadRepository;
	
	@Override
	public Project createProject(Long leadId,Long estimateId) {

		ServiceDetails estimate = serviceDetailsRepository.findById(estimateId).get();
		Optional<Lead> leadOp = leadRepository.findById(leadId);
		Lead lead =leadOp.get();
		List<Client> c = lead.getClients().stream().filter(i->i.getServiceDetails().contains(estimate)).collect(Collectors.toList());
		Client client =c.get(0); 
		 Project project = new Project();
		project.setAssignee(lead.getAssignee());
		project.setCompany(estimate.getCompanies());
		project.setAmount(estimate.getProfessionalFees());
		project.setClient(client);
		project.setName(estimate.getName());
//		project.setProjectNo(null);  //no need to setup 
		project.setServiceDetails(estimate);
		project.setCreateDate(new Date());
		projectRepository.save(project);
		return project;
	}

	@Override
	public Project getAllProject() {
		// TODO Auto-generated method stub
		return null;
	}

}
