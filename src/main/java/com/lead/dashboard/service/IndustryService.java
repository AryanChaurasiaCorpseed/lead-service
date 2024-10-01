package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Industry;
import com.lead.dashboard.dto.IndustryDto;

@Service
public interface IndustryService {

	Boolean createIndustry(IndustryDto industryDto);

	List<Industry> getAllIndustry();

}
