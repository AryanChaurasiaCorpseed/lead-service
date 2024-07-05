package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	LeadRepository leadRepository;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public Company createCompany(CompanyDto companyDto) {
		// TODO Auto-generated method stub
	
		Company company = new Company();
		company.setName(companyDto.getName());
		company.setCompanyAge(companyDto.getCompanyAge());
		company.setGstNo(companyDto.getGstNo());
		company.setPanNo(companyDto.getPanNo());
		company.setAddress(companyDto.getAddress());
		company.setCity(companyDto.getCity());
		company.setCountry(companyDto.getCountry());
		company.setState(companyDto.getState());
		List<Project> projectList = projectRepository.findAllByIdIn(companyDto.getProjectId());
		company.setCompanyProject(projectList);
		List<Lead> leadList=leadRepository.findAllByIdIn(companyDto.getLeadId());
		User user = userRepo.findById(companyDto.getAssigneeId()).get();
		company.setAssignee(user);
		company.setCompanyLead(leadList);
		companyDto.getAssigneeId();
		companyRepository.save(company);
		return company;
	}

	@Override
	public Company getCompany(Long companyId) {
		// TODO Auto-generated method stub
		Optional<Company> company = companyRepository.findById(companyId);
		return company.get();
	}

	@Override
	public List<Map<String,Object>> getAllCompany(Long userId) {
         Optional<User> userOp = userRepo.findById(userId);
         List<Company> companyList = new ArrayList<>();
         User user = userOp.get();
         List<String> roleList = user.getRole();
         if(roleList.contains("ADMIN")) {
     		 companyList = companyRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());

         }else {
        	 if(user.isManager()) {
        		 List<Long>userList = new ArrayList<>();
        		 userList.add(userId);
        		List<Long> uList = userServiceImpl.getUserManager(userId);
        		 userList.addAll(uList);
            	 companyList =companyRepository.findAllByAssigneeIdIn(userList);

        	 }else {
            	 
        		 companyList =companyRepository.findByAssigneeId(userId);

        	 }
         }
//		List<Company> companyList = companyRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		List<Map<String,Object>>res = new ArrayList<>();
		for(Company c:companyList) {
			Map<String,Object>result = new HashMap<>();

			result.put("companyId", c.getId());
			result.put("companyName", c.getName());
			result.put("country", c.getCountry());
			result.put("gstNo", c.getGstNo());
			result.put("assignee", c.getAssignee());
			result.put("state", c.getState());
			result.put("city", c.getCity());
			result.put("address", c.getAddress());

			List<Lead> lList = c.getCompanyLead();
			List<Map<String,Object>>leadList = new ArrayList<>();
			for(Lead l:lList) {
				Map<String,Object>lMap = new HashMap<>();
				lMap.put("leadId", l.getId());
				lMap.put("leadNameame", l.getLeadName());
				leadList.add(lMap);
				
			}
			result.put("lead", leadList);
			
			List<Project> pList = c.getCompanyProject();
			List<Map<String,Object>>projectList = new ArrayList<>();
			for(Project p:pList) {
				Map<String,Object>pMap = new HashMap<>();
				pMap.put("projectId", p.getId());
				pMap.put("projectName", p.getName());
				projectList.add(pMap);				
			}
			result.put("project", projectList);
			res.add(result);

		}
		return res;
	}

	@Override
	public boolean deleteCompany(Long companyId) {
		// TODO Auto-generated method stub
		Optional<Company> companyOp = companyRepository.findById(companyId);
		boolean flag=false;
         if(companyOp.get()!=null) {
        	Company company = companyOp.get();
        	company.setDeleted(true);
        	companyRepository.save(company);
        	flag = true;
         }
		return flag;
	}

	@Override
	public List<Map<String, Object>> getAllProjectByCompany(Long companyId) {
		Optional<Company> companyOp = companyRepository.findById(companyId);
		List<Map<String, Object>> result = new ArrayList<>();
         if(companyOp.get()!=null) {
        	 Company company = companyOp.get();
        	 List<Project>projectList= company.getCompanyProject();
        	 for(Project p:projectList) {
        		 Map<String, Object> map = new HashMap<>();
        		 map.put("projectId", p.getId());
        		 map.put("projectName", p.getName());
        		 map.put("assignee", p.getAssignee());
        		 map.put("product", p.getProduct());
        		 result.add(map);
        	 }
         }
		return result;
	}

	@Override
	public List<Map<String, Object>> getAllLeadByCompany(Long companyId) {
		
		Optional<Company> companyOp = companyRepository.findById(companyId);
		List<Map<String, Object>> result = new ArrayList<>();
         if(companyOp.get()!=null) {
        	 Company company = companyOp.get();
        	 List<Lead>leadList= company.getCompanyLead();
        	 for(Lead l:leadList) {
        		 Map<String, Object> map = new HashMap<>();
        		 map.put("leadId", l.getId());
        		 map.put("leadName", l.getLeadName());
        		 map.put("assignee", l.getAssignee());
        		 map.put("client", l.getName());
        		 map.put("ipAddress", l.getIpAddress());
     			map.put("originalName", l.getOriginalName());
     			map.put("categoryId", l.getCategoryId());
     			map.put("city", l.getCity());
     			map.put("assigne", l.getAssignee());
     			map.put("displayStatus", l.getDisplayStatus());
     			map.put("description", l.getLeadDescription());
     			map.put("email", l.getEmail());
     			map.put("ipAddress", l.getIpAddress());
     			map.put("remarks", l.getRemarks());
     			map.put("status", l.getStatus());
     			map.put("isBacklog", l.isBacklogTask());

        		 result.add(map);
        	 }
         }
		return result;
	}

}
