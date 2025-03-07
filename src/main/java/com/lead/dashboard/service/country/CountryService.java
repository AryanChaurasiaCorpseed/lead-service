package com.lead.dashboard.service.country;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.dto.CountryStateDto;

@Service
public interface CountryService {


	Boolean createCountry(String name);

	List<Map<String, Object>> getAllCountry();

	Boolean addStateInCountry(CountryStateDto countryStateDto);

}
