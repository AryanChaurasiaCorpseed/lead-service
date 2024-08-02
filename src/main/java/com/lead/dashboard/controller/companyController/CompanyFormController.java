package com.lead.dashboard.controller.companyController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.repository.CompanyFormRepo;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CompanyFormController {
	
	@Autowired
	CompanyFormRepo companyFormRepo;
	
	@Autowired
	LeadRepository leadRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProjectRepository projectRepository;
	
	
	@PostMapping(UrlsMapping.CREATE_COMPANY_FORM)
	public CompanyForm createCompanyForm(@RequestBody CreateFormDto createFormDto)
	{				
		CompanyForm companyForm =  new CompanyForm();
		
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());
		
		companyForm.setIsUnit(createFormDto.getIsUnit());
		companyForm.setUnitName(createFormDto.getUnitName());
		companyForm.setUnitId(createFormDto.getUnitId());
		
		companyForm.setAddress(createFormDto.getAddress());
		companyForm.setAssigneeId(createFormDto.getAssigneeId());
		
		companyForm.setCity(createFormDto.getCity());
		companyForm.setCompanyAge(createFormDto.getCompanyAge());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());
		companyForm.setCountry(createFormDto.getCountry());
		
		companyForm.setGstType(createFormDto.getGstType());
		companyForm.setGstDocuments(createFormDto.getGstDocuments());
		companyForm.setGstNo(createFormDto.getGstNo());
		companyForm.setCompanyAge(createFormDto.getGstNo());
		
		companyForm.setSAddress(createFormDto.getSAddress());
		companyForm.setSCity(createFormDto.getSCity());
		companyForm.setSState(createFormDto.getSState());
		companyForm.setSCountry(createFormDto.getSCountry());
		
		companyForm.setStatus(createFormDto.getStatus());
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setIsUnit(createFormDto.getIsUnit());
		
		companyForm.setPanNo(createFormDto.getPanNo());
		
		companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
		companyForm.setContactName(createFormDto.getContactName());
		companyForm.setContactEmails(createFormDto.getContactEmails());
		companyForm.setContactNo(createFormDto.getContactNo());
		companyForm.setContactWhatsappNo(createFormDto.getContactWhatsappNo());
		
		companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
		System.out.println(createFormDto.getSContactName());
		companyForm.setSContactName(createFormDto.getSContactName());
		System.out.println(createFormDto.getSContactEmails());

		companyForm.setSContactEmails(createFormDto.getSContactEmails());
		companyForm.setSContactNo(createFormDto.getSContactNo());
		companyForm.setSContactWhatsappNo(createFormDto.getSContactWhatsappNo());
        if(createFormDto.getUpdatedBy()!=null) {
        	User user = userRepo.findById(createFormDto.getUpdatedBy()).get();
        	companyForm.setUpdatedBy(user);
        }
		
		Lead lead = leadRepository.findById(createFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(createFormDto.getPanNo());
		companyForm.setState(createFormDto.getState());
	    companyFormRepo.save(companyForm);
	   return companyForm;
	}
	@GetMapping(UrlsMapping.GET_ALL_COMPANY_FORM)
	public List<Map<String,Object>> getAllCompanyForm(@RequestParam String status)
	{
		List<Map<String,Object>>result = new ArrayList<>();
		List<CompanyForm> compList = companyFormRepo.findAll();
//		List<CompanyForm> companyList = companyFormRepo.findAllByStatus(status);
		for(CompanyForm c:compList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", c.getId());
			map.put("unitName", c.getUnitName());
//			map.put("primaryAddress", c.get);
			map.put("companyName", c.getCompanyName());
			map.put("lead", c.getLead());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDocuments", c.getGstDocuments());
			map.put("companyAge", c.getCompanyAge());
			map.put("status", c.getStatus());
			map.put("updatedBy", c.getUpdatedBy());

			map.put("city", c.getCity());
			map.put("address", c.getAddress());
			map.put("state", c.getState());
			map.put("country", c.getCountry());
			
			map.put("sCity", c.getSCity());
			map.put("sAddress", c.getSAddress());
			map.put("sState", c.getSState());
			map.put("sCountry", c.getSCountry());


			result.add(map);

		}
		return result;
	}
	
	@GetMapping(UrlsMapping.CHECK_COMPANY_EXIST)
	public Company checkCompanyExist(Long leadId)
	{
		Company company=null;
		 Long compId = companyRepository.findCompanyIdByLeadId(leadId);
		 if(compId!=null) {
			 Optional<Company> comp = companyRepository.findById(compId);
			 if(comp!=null && comp.get()!=null) {
				 company=comp.get();
			 }
		 }
		
		return company;
	}
	@PutMapping(UrlsMapping.UPDATE_COMPANY_FORM_STATUS)
	public Boolean AccountApprovalOnInvoice(String status,Long id,Long currentUserId){
		CompanyForm companyForm = companyFormRepo.findById(id).get();
		Boolean flag=false;
		if("approved".equals(status)) {
			if(companyForm.getIsPresent()) {
				Company parentCompany = companyRepository.findById(companyForm.getCompanyId()).get();
				if(companyForm.getIsUnit()) {
					Company unit = companyRepository.findById(id).get();
					List<Lead> leadList = unit.getCompanyLead();
					leadList.add(companyForm.getLead());
					unit.setCompanyLead(leadList);
					unit.setStatus("open");
					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					User assignee = unit.getAssignee();
					
					p.setAssignee(assignee);
					p.setStatus("initiated");
					p.setCreateDate(new Date());
					List<Project> projectList = unit.getCompanyProject();
					projectList.add(p);
					unit.setCompanyProject(projectList);
					if(currentUserId!=null) {
			        	User user = userRepo.findById(currentUserId).get();
			        	companyForm.setUpdatedBy(user);
			        }
					p.setCompany(unit);
					companyRepository.save(unit);
					companyForm.setStatus(status);
		            companyFormRepo.save(companyForm);
		            flag=true;
					
				}else {
					Company unit = new Company();
					unit.setName(companyForm.getCompanyName());
//					company.setAssignee(assignee);
			        unit.setCompanyAge(companyForm.getCompanyAge());
			        unit.setStatus("open");
			        
			        
					unit.setGstNo(companyForm.getGstNo());
					unit.setGstType(companyForm.getGstType());
					unit.setGstDocuments(companyForm.getGstDocuments());
					unit.setCompanyAge(companyForm.getCompanyAge());
					
					unit.setAddress(companyForm.getAddress());
					unit.setCity(companyForm.getCity());
					unit.setState(companyForm.getState());
					unit.setCountry(companyForm.getCountry());
					
					unit.setSAddress(companyForm.getSAddress());
					unit.setSCity(companyForm.getSCity());
					unit.setSState(companyForm.getSState());
					unit.setSCountry(companyForm.getSCountry());
					if(currentUserId!=null) {
			        	User user = userRepo.findById(currentUserId).get();
			        	companyForm.setUpdatedBy(user);
			        }
					Lead lead = companyForm.getLead();
					List<Lead>leadList =new ArrayList<>();
					leadList.add(lead);
					unit.setCompanyLead(leadList);
					unit.setParent(false);
					unit.setParent(parentCompany);
					
					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					User assignee = unit.getAssignee();
					
					p.setAssignee(assignee);
					p.setStatus("initiated");
					p.setCreateDate(new Date());
					List<Project> projectList = unit.getCompanyProject();
					projectList.add(p);
					unit.setCompanyProject(projectList);
//					p.setCompany(unit);
					companyRepository.save(unit);
					companyForm.setStatus(status);
		            companyFormRepo.save(companyForm);
		            flag=true;

				}

				
			}else {
				User assignee = userRepo.findById(companyForm.getAssigneeId()).get();
				
				Company company = new Company();
				company.setName(companyForm.getCompanyName());
				company.setAssignee(assignee);
				
				company.setGstNo(companyForm.getGstNo());
				company.setGstType(companyForm.getGstType());
				company.setGstDocuments(companyForm.getGstDocuments());		
				company.setCompanyAge(companyForm.getCompanyAge());
				company.setAssignee(assignee);
				company.setStatus("open");
				Lead lead = companyForm.getLead();
				List<Lead>leadList = new ArrayList<>();
				if(lead!=null) {
					leadList.add(lead);

				}
				leadList.add(lead);
				company.setCompanyLead(leadList);
				company.setParent(true);
				if(currentUserId!=null) {
		        	User user = userRepo.findById(currentUserId).get();
		        	companyForm.setUpdatedBy(user);
		        }
				//Assignee
				
				Project p = new Project();
				p.setName(companyForm.getLead().getName());
				p.setLead(companyForm.getLead());
				
				p.setAssignee(assignee);
				p.setStatus("initiated");
				p.setCreateDate(new Date());
				List<Project> projectList =new ArrayList<>();
				projectList.add(p);
				company.setCompanyProject(projectList);
//				p.setCompany(unit);
				companyRepository.save(company);
				companyForm.setStatus(status);
	            companyFormRepo.save(companyForm);
	            flag=true;

			}
			
		}else {
			companyForm.setStatus(status);
            companyFormRepo.save(companyForm);
            flag=true;

		}
		return flag;
	}
	
	
	@GetMapping(UrlsMapping.GET_ALL_COMPANY_FORM_BY_STATUS)
	public List<Map<String,Object>> getAllCompanyFormByStatus(@RequestParam String status,@RequestParam Long userId)
	{
		List<Map<String,Object>>result = new ArrayList<>();
//		List<CompanyForm> compList = companyFormRepo.findAll();
		 Optional<User> user = userRepo.findById(userId);
		 List<CompanyForm> compList  = new ArrayList<>();
		if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
			compList = companyFormRepo.findAllByStatus(status);
		}else {
			compList = companyFormRepo.findAllByStatusAndassigneeId(status,userId);
		}
//		
		for(CompanyForm c:compList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", c.getId());
			map.put("unitName", c.getUnitName());
//			map.put("primaryAddress", c.get);
			map.put("companyName", c.getCompanyName());
			map.put("lead", c.getLead());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDocuments", c.getGstDocuments());
			map.put("companyAge", c.getCompanyAge());
			map.put("status", c.getStatus());
			map.put("updatedBy", c.getUpdatedBy());
			map.put("city", c.getCity());
			map.put("address", c.getAddress());
			map.put("state", c.getState());
			map.put("country", c.getCountry());
			
			map.put("sCity", c.getSCity());
			map.put("sAddress", c.getSAddress());
			map.put("sState", c.getSState());
			map.put("sCountry", c.getSCountry());


			result.add(map);

		}
		return result;
	}
	
	
	
	
	
	
	

}
