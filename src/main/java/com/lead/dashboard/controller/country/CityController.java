package com.lead.dashboard.controller.country;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.service.CityService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CityController {

	@Autowired
	CityService cityService;
	
	@PostMapping(UrlsMapping.CREATE_CITY)
	public Boolean createCity(@RequestParam String name)
	{
		Boolean city= cityService.createCity(name);
		return city;
	}
	
	@GetMapping(UrlsMapping.GET_ALL_CITY)
	public List<Map<String,Object>> getAllCity()
	{
		List<Map<String,Object>> country= cityService.getAllCity();
		return country;
	}
}
