package com.lead.dashboard.controller.industryController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Industry;
import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.dto.IndustryDto;
import com.lead.dashboard.service.IndustryService;
@RestController
public class IndustryController {

	@Autowired
	IndustryService industryService;
	
	@PostMapping("/leadService/api/v1/industryData/createIndustry")
	public Boolean createIndustry(@RequestBody IndustryDto industryDto){	
		Boolean res= industryService.createIndustry(industryDto);
		return res;
	}
	
	@GetMapping("/leadService/api/v1/industryData/getAllIndustry")
	public List<Industry> getAllIndustry(){	
		List<Industry> res= industryService.getAllIndustry();
		return res;
	}
	@GetMapping("/leadService/api/v1/industryData/getAllIndustryCount")
	public int getAllIndustryCount(){	
		 int res = industryService.getAllIndustry().size();
		return res;
	}
	

	@GetMapping("/leadService/api/v1/industryData/getAllIndustryForIndustryPage")
	public List<Industry> getAllIndustryForIndustryPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){	
		List<Industry> res= industryService.getAllIndustry(page-1,size);
		return res;
	}
	
	
	@GetMapping("/leadService/api/v1/industryData/getSubIndustryByIndustryId")
	public List<Map<String,Object>> getSubIndustryByIndustryId(@RequestParam Long id){	
		List<Map<String,Object>> res= industryService.getSubIndustryByIndustryId(id);
		return res;
	}
}
