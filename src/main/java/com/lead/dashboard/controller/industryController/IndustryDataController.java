package com.lead.dashboard.controller.industryController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.service.IndustryDataService;

@RestController
@RequestMapping("/leadService")
public class IndustryDataController {

	@Autowired
	IndustryDataService industryDataService;
	
	@PostMapping("/api/v1/industryData/createIndustryData")
	public Boolean createIndustryData(@RequestParam String name){	
		Boolean res= industryDataService.createIndustryData(name);
		return res;
	}
	
	@GetMapping("/api/v1/industryData/getAllIndustryData")
	public List<IndustryData> getAllIndustryData(){	
		List<IndustryData> res= industryDataService.getAllIndustryData();
		return res;
	}  
	
	@GetMapping("/api/v1/industryData/getAllIndustryDataForPage")
	public List<IndustryData> getAllIndustryDataForPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size){	
		List<IndustryData> res= industryDataService.getAllIndustryDataForPage(page,size);
		return res;
	}  
	
	@GetMapping("/api/v1/industryData/getAllIndustryDataCount")
	public int getAllIndustryDataCount(){	
		int res= industryDataService.getAllIndustryData()!=null?industryDataService.getAllIndustryData().size():0;;
		return res;
	}  
}
