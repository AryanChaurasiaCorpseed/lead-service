package com.lead.dashboard.controller.industryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.domain.Industry;
import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.dto.IndustryDto;
import com.lead.dashboard.service.IndustryService;

public class IndustryController {

	@Autowired
	IndustryService industryService;
	
	@PostMapping("/api/v1/industryData/createIndustry")
	public Boolean createIndustry(@RequestBody IndustryDto industryDto){	
		Boolean res= industryService.createIndustry(industryDto);
		return null;
	}
	
	@GetMapping("/api/v1/industryData/getAllIndustry")
	public List<Industry> getAllIndustry(){	
		List<Industry> res= industryService.getAllIndustry();
		return null;
	}
}
