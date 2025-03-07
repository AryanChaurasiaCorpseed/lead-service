package com.lead.dashboard.controller.country;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.dto.CountryStateDto;
import com.lead.dashboard.service.country.CountryService;
import com.lead.dashboard.util.UrlsMapping;

   
@RestController
public class CountryController {
	
	
	@Autowired
	CountryService countryService;
	
	@PostMapping(UrlsMapping.CREATE_COUNTRTY)
	public Boolean createCountry(@RequestParam String name)
	{
		Boolean country= countryService.createCountry(name);
		return country;
	}
	
	@GetMapping(UrlsMapping.GET_ALL_COUNTRTY)
	public List<Map<String,Object>> getAllCountry()
	{
		List<Map<String,Object>> country= countryService.getAllCountry();
		return country;
	}
    
	@PostMapping(UrlsMapping.ADD_STATE_IN_COUNTRY)
	public Boolean addStateInCountry(@RequestBody CountryStateDto countryStateDto)
	{
		Boolean country= countryService.addStateInCountry(countryStateDto);
		return country;
	}

}
