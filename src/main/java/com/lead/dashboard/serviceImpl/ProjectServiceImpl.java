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
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.CompanyRepository;
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
	CompanyRepository companyRepository;
	
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
		project.setLead(lead);
		project.setName(estimate.getName());
//		project.setProjectNo(null);  //no need to setup 
		project.setServiceDetails(estimate);
		project.setCreateDate(new Date());
		projectRepository.save(project);
		return project;
	}


	public Project createProjectForTesting(Long leadId) {
		Project p = new Project();
		Optional<Lead> leadOp = leadRepository.findById(leadId);
        if(leadOp.get()!=null) {
        	Lead l =leadOp.get();
    		p.setName(l.getLeadName());
    		p.setAmount(0);
    		p.setLead(l);
    		projectRepository.save(p);

        }
        return p;      
	}
	@Override
	public Project getAllProject() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Project createProjectV2(Long leadId) {
		Project p = new Project();
		Optional<Lead> leadOp = leadRepository.findById(leadId);
		Long companyId = companyRepository.findCompanyIdByLeadId(leadId);
        if(leadOp.get()!=null) {
        	Lead l =leadOp.get();
    		p.setName(l.getLeadName());
    		p.setAmount(0);
    		p.setLead(l);
   		    p=projectRepository.save(p);

    		Company company=null;
    		if(companyId!=null) {
    			company = companyRepository.findById(companyId).get();
    			
    			List<Project> proList=new ArrayList<>();
    			proList.add(p);		
    			proList.addAll(company.getCompanyProject());
    			company.setCompanyProject(proList);
    			
    			
    		}else {
    		    company =new Company();
    			company.setName(l.getLeadName());
    			
    			List<Lead>leadList = new ArrayList<>();
    			leadList.add(l);
    			company.setCompanyLead(leadList);
    		}
//    		 company.set
			companyRepository.save(company);


        }
        return p;  
	}

}
