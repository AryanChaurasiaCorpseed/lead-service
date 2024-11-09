package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubIndustryDto;

@Service
public interface SubIndustryService {

	Boolean createSubIndustry(SubIndustryDto subIndustryDto);

	List<SubIndustry> getAllSubIndustry();

	List<SubIndustry> getAllSubIndustryForPage(int page, int size);

	int getAllSubIndustryCount();

	List<Map<String,Object>> getAllSubSubIndustryBySubIndustryId(Long id);


}
