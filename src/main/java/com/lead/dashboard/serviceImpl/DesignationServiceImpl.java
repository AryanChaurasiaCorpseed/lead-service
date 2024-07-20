package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.repository.DesignationRepo;
import com.lead.dashboard.service.DesignationService;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesignationRepo designationRepo;
	
	@Override
	public List<Designation> getAllDesignation() {
		
		List<Designation> designationList=designationRepo.findAllByIsDeleted(false);
		return designationList;
	}

	@Override
	public Designation createDesignation(String name) {
		Designation designation = new Designation();
		designation.setName(name);
		designation.setDeleted(false);
		designationRepo.save(designation);
		return designation;
	}


}
