package com.lead.dashboard.serviceImpl.country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Country;
import com.lead.dashboard.domain.State;
import com.lead.dashboard.dto.CountryStateDto;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.CountryRepo;
import com.lead.dashboard.repository.StateRepository;
import com.lead.dashboard.service.country.CountryService;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	CountryRepo countryRepo;
	
	@Autowired
	StateRepository  stateRepository;

	@Override
	public Boolean createCountry(String name) {
		Boolean  flag=false;
		Country  c=new Country();
		c.setName(name);
		countryRepo.save(c);
		flag=true;
		return flag;
	}

	@Override
	public List<Map<String, Object>> getAllCountry() {
		List<Country> countryList = countryRepo.findAll();
		List<Map<String, Object>>res= new ArrayList<>();
		for(Country c:countryList) {
			Map<String, Object>map = new HashMap<>();
			map.put("id", c.getId());
			map.put("name", c.getName());
			res.add(map);
		}
		return res;
	}

	@Override
	public Boolean addStateInCountry(CountryStateDto countryStateDto) {
		Boolean flag=false;
//		Long countryId = countryStateDto.getCountryId();
		System.out.println("countryId....."+countryStateDto.getCountryId());
		Country country = countryRepo.findById(countryStateDto.getCountryId()).get();
		List<State> stateList = stateRepository.findAllByIdIn(countryStateDto.getStateId());
		country.setCountryState(stateList);
		countryRepo.save(country);
		flag=true;
		return flag;
	}
	

}
