package com.lead.dashboard.controller.industryController;

import java.util.List;
import java.util.Map;

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
@RequestMapping("/leadService")
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
	
	
	@GetMapping("/api/v1/industryData/getAllSubSubIndustryForPage")
	public List<SubSubIndustry> getAllSubSubIndustryForPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){	
		List<SubSubIndustry> res= subSubIndustryService.getAllSubSubIndustryForPage(page-1,size);
		return res;
	}
	
	
	@GetMapping("/api/v1/industryData/getAllSubSubIndustryCount")
	public int getAllSubSubIndustryCount(){	
		int res= subSubIndustryService.getAllSubSubIndustry().size();
		return res;
	}
	
	@GetMapping("/api/v1/industryData/getAllIndustryDataBySubSubIndustryId")
	public List<Map<String,Object>> getAllIndustryDataBySubSubIndustryId(@RequestParam Long id){	
		List<Map<String,Object>> res= subSubIndustryService.getAllIndustryDataBySubSubIndustryId(id);
		 
		return res;
	}
	
}
