package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.dto.CompanyDto;

@Service
public interface CompanyService {

	Company createCompany(CompanyDto companyDto);

	Company getCompany(Long companyId);

	List<Company> getAllCompany();

	boolean deleteCompany(Long companyId);

}
