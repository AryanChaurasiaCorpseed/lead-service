package com.lead.dashboard.controller.industryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubSubIndustryDto;
import com.lead.dashboard.service.SubSubIndustryService;
@RestController
@RequestMapping("/leadService/")
public class SubSubIndustryController {

	@Autowired
	SubSubIndustryService subSubIndustryService;
	
	
	
	@PostMapping("/api/v1/industryData/createSubSubIndustry")
	public Boolean createSubSubIndustry(@RequestBody SubSubIndustryDto subSubIndustryDto){	
		Boolean res= subSubIndustryService.createSubSubIndustry(subSubIndustryDto);
		return res;
	}
	
	@GetMapping("/api/v1/industryData/getAllSubSubIndustry")
	public List<SubSubIndustry> getAllSubSubIndustry(){	
		List<SubSubIndustry> res= subSubIndustryService.getAllSubSubIndustry();
		return res;
	}
	
	
}
