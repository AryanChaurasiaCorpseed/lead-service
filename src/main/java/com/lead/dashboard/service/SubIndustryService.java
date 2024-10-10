package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.dto.SubIndustryDto;

@Service
public interface SubIndustryService {

	Boolean createSubIndustry(SubIndustryDto subIndustryDto);

	List<SubIndustry> getAllSubIndustry();


}
