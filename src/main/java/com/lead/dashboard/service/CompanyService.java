package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.dto.CompanyDto;

@Service
public interface CompanyService {

	Company createCompany(CompanyDto companyDto);

	Company getCompany(Long companyId);

	List<Map<String,Object>> getAllCompany(Long userId);

	boolean deleteCompany(Long companyId);

	List<Map<String, Object>> getAllProjectByCompany(Long companyId);

	List<Map<String, Object>> getAllLeadByCompany(Long companyId);

}
