package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.City;
import com.lead.dashboard.repository.CityRepository;
import com.lead.dashboard.service.CityService;
@Service
public class CityServiceImpl implements CityService{

	@Autowired
	CityRepository cityRepository;
	
	@Override
	public Boolean createCity(String name) {
		
		Boolean flag=false;
		City city =new City();
		city.setName(name);
		cityRepository.save(city);
		flag=true;
		
		return flag;
	}

	@Override
	public List<Map<String, Object>> getAllCity() {
		List<City> cityList = cityRepository.findAll();
		List<Map<String, Object>>res=new ArrayList<>();
		for(City city:cityList) {
			Map<String, Object>map = new HashMap<>();
			map.put("id", city.getId());
			map.put("name", city.getName());
			res.add(map);
		}
			
		return res;
	}

}
