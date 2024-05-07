package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Company;
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
	public List<Company> getAllCompany() {
 
		List<Company> companyList = companyRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		return companyList;
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
