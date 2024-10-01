package com.lead.dashboard.service;

import java.util.List;

import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubSubIndustryDto;

public interface SubSubIndustryService {

	Boolean createSubSubIndustry(SubSubIndustryDto subSubIndustryDto);

	List<SubSubIndustry> getAllSubSubIndustry();


}
