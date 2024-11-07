package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Industry;
import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.dto.IndustryDto;

@Service
public interface IndustryService {

	Boolean createIndustry(IndustryDto industryDto);

	List<Industry> getAllIndustry();

	List<Industry> getAllIndustry(int page, int size);

	List<Map<String,Object>> getSubIndustryByIndustryId(Long id);

}
