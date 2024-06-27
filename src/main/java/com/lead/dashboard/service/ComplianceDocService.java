package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ComplianceDocService {

	public List<Map<String,Object>>getAllComplianceDocuments();

}
