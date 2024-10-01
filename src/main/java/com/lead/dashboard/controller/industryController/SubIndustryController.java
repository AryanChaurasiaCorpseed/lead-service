package com.lead.dashboard.controller.industryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubIndustryDto;
import com.lead.dashboard.dto.SubSubIndustryDto;
import com.lead.dashboard.service.SubIndustryService;

@RestController
@RequestMapping("/leadService/")
public class SubIndustryController {
	
	@Autowired
	SubIndustryService subIndustryService;

	@PostMapping("/api/v1/industryData/createSubIndustry")
	public Boolean createSubIndustry(@RequestBody SubIndustryDto subIndustryDto){	
		Boolean res= subIndustryService.createSubIndustry(subIndustryDto);
		return res;
	}
	
	@GetMapping("/api/v1/industryData/getAllSubIndustry")
	public List<SubIndustry> getAllSubIndustry(){	
		List<SubIndustry> res= subIndustryService.getAllSubIndustry();
		return res;
	}
	
}
