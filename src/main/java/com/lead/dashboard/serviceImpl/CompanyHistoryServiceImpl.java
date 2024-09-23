package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.repository.CompanyHistoryRepo;
import com.lead.dashboard.service.CompanyHistoryService;

@Service
public class CompanyHistoryServiceImpl implements CompanyHistoryService{

	
	@Autowired
	CompanyHistoryRepo companyHistoryRepo;
	
	@Override
	public List<CompanyDataHistory> getAllCompanyHistory(Long companyId) {
		List<CompanyDataHistory>task=companyHistoryRepo.findAllByCompanyId(companyId);
		return task;
	}

}
