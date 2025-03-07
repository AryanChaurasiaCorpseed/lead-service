package com.lead.dashboard.controller.country;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.repository.StateRepository;
import com.lead.dashboard.service.country.StateService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class StateController {
	
	@Autowired
	StateService  stateService;
	
	@PostMapping(UrlsMapping.CREATE_STATE)
	public Boolean createState(@RequestParam String name)
	{
		Boolean state= stateService.createState(name);
		return state;
	}
	
	@GetMapping(UrlsMapping.GET_ALL_STATE)
	public List<Map<String,Object>> getAllState()
	{
		List<Map<String,Object>> stateList= stateService.getAllState();
		return stateList;
	}

}
