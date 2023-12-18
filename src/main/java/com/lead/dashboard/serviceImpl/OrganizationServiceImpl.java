package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.Organization.Organization;
import com.lead.dashboard.domain.Organization.Plans;
import com.lead.dashboard.domain.Organization.UserManagment;
import com.lead.dashboard.dto.CreateOrganization;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.OrganizationRepository;
import com.lead.dashboard.repository.PlansRepository;
import com.lead.dashboard.repository.RoleRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.OrganizationService;
import com.lead.dashboard.util.CorpseedCalender;
import com.netflix.discovery.converters.Auto;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	PlansRepository plansRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Organization createOrganization(CreateOrganization createOrganization) {
		Organization organization = new Organization();
		Plans plans = plansRepository.findById(createOrganization.getPlansId()).get();
		organization.setName(createOrganization.getOrganizationName());
		organization.setIndustryName(createOrganization.getIndustryName());
		if (createOrganization.getPlanType().equals("FREE")) {
			organization.setTypePlan(createOrganization.getPlanType());
			organization.setTeamSize(5L);
			organization.setIsPlanActiveOrNot(true);
			organization.setPlansStart(new Date());
			organization.setPlansEnd(CorpseedCalender.addDate(new Date(), 21));
		}else {
			organization.setTypePlan(createOrganization.getPlanType());
			organization.setTeamSize(5L);
			organization.setSubscribedPlans(plans);
			organization.setIsPlanActiveOrNot(true);
			organization.setPlansStart(new Date());
			organization.setPlansEnd(CorpseedCalender.addDate(new Date(), 21)); // need to change
		}
		Role r = new Role();
		r.setName("ADMIN");
		roleRepository.save(r);
		User user=userRepo.findById(createOrganization.getUserId()).get();
		UserManagment userManagment=new UserManagment();
		List<UserManagment>um = new ArrayList<>();
		userManagment.setRole(r);
		userManagment.setUser(user);
		um.add(userManagment);
		List<Role>roleList = new ArrayList<>();
		roleList.add(r);
		organization.setRoles(roleList);
		organization.setOrganizationUserManagment(um);
		organizationRepository.save(organization);
		return organization;
	}

	public void createByDefaultRoles(Long organizationId) {
		Organization organization = organizationRepository.findById(organizationId).get();
		List<Role>roleList=organization.getRoles();
		Role r1 = new Role();
		r1.setName("User");
		roleRepository.save(r1); 
		
		Role r2 = new Role();
		r2.setName("Leads");
		roleRepository.save(r2); 
		roleList.add(r1);
		roleList.add(r2);
		organizationRepository.save(organization);
		
		
	}

	@Override
	public Organization getOrganization(Long orgId) {
		return organizationRepository.findById(orgId).get();
	}



}
