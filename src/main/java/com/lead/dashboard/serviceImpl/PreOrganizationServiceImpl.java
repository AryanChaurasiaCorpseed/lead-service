package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.Organization.Organization;
import com.lead.dashboard.repository.OrganizationRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.service.PreOrganizationService;

@Service
public class PreOrganizationServiceImpl implements PreOrganizationService {

	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	
	@Override
	public boolean createPreOrganizationStatus(Long organizationId) {
		Organization organization = organizationRepository.findById(organizationId).get();
		List<Status>statusList = new ArrayList<>();

		Status s1 = new Status(); 
		s1.setName("New");
		statusList.add(s1);
        
		Status s2 = new Status(); 
		s2.setName("Follow Up");
		statusList.add(s2);
        
		Status s3 = new Status(); 
		s3.setName("Proposal Send");
		statusList.add(s3);
		
		Status s4 = new Status(); 
		s4.setName("Deal Lost");
		statusList.add(s4);
		
		Status s5 = new Status(); 
		s5.setName("Bad Fit");
		statusList.add(s5);
		List<Status> listStatus = organization.getOrganizationStatus();
		listStatus=statusList;
		organization.setOrganizationStatus(listStatus);
		organizationRepository.save(organization);
  		
		return true;
	}
	

}
