package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.UpdateCompanyDto;

@Service
public interface CompanyService {

	Company createCompany(CompanyDto companyDto) ;

	Company getCompany(Long companyId);

	List<Map<String,Object>> getAllCompany(Long userId);

	boolean deleteCompany(Long companyId);

	List<Map<String, Object>> getAllProjectByCompany(Long companyId);

	List<Map<String, Object>> getAllLeadByCompany(Long companyId);

	List<Map<String, Object>> getAllParentCompany();

	List<Map<String, Object>> getAllCompanyUnit(Long id);

	List<Company> getCompanyByGst(String gst);
 
	Map<String, Object> getCompanyById(Long id);

	boolean updateCompanyAssignee(Long companyId, Long assigneeId,Long currentUserId);



	List<Map<String, Object>> searchCompanyByNameAndGST(String searchNameAndGSt, Long userId);

	List<Map<String, Object>> getAllCompanyV2(Long userId, Long filterUserId, int page, int size);

	boolean updateMultiCompanyAssignee(UpdateCompanyDto updateCompanyDto);

	boolean updateMultiCompanyTempAssignee(UpdateCompanyDto updateCompanyDto);

	List<Map<String, Object>> getAllTempCompany(Long userId, Long filterUserId, int i, int size);

	boolean deleteTempAssignee(UpdateCompanyDto updateCompanyDto);

	List<Map<String, Object>> getAllConsultantByCompany(Long userId,int i, int size);

	int getAllConsultantByCompanyCount(Long userId);

	List<Map<String, Object>> companySearchByGstAndContactDetails(String searchNameAndGSt, Long userId,
			String fieldSearch);
}
