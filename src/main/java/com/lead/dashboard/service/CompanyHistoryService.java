package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.serviceImpl.CompanyDataHistory;

@Service
public interface CompanyHistoryService {

	List<CompanyDataHistory> getAllCompanyHistory(Long companyId);

}
