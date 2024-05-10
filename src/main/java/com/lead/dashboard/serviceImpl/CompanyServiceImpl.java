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
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	LeadRepository leadRepository;
	
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
		List<Lead> leadList=leadRepository.findAllByIdIn(companyDto.getLeadId());
		company.setCompanyLead(leadList);
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
	public List<Map<String,Object>> getAllCompany() {
 
		List<Company> companyList = companyRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		List<Map<String,Object>>res = new ArrayList<>();
		for(Company c:companyList) {
			Map<String,Object>result = new HashMap<>();

			result.put("companyId", c.getId());
			result.put("companyName", c.getName());
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

}
