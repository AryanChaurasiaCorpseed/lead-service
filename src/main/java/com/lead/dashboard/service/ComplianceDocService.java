package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.dto.CreateCategoryDocDto;

@Service
public interface ComplianceDocService {

	public List<Map<String,Object>>getAllComplianceDocuments();

	public Boolean createDocumentInCategory(Long categoryId,String docList);

	public List<Map<String, Object>> getAllComplianceDocuments(int page, int size);

	public void moveComplianceDoc();

	public Map<String, Object> getComplianceDocByName(String name);

}
