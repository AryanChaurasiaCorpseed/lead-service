package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Industry;
import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.dto.IndustryDto;
import com.lead.dashboard.repository.IndustryRepo;
import com.lead.dashboard.repository.SubIndustryRepo;
import com.lead.dashboard.service.IndustryService;
@Service
public class IndustryServiceImpl implements IndustryService{
	
	@Autowired
	SubIndustryRepo subIndustryRepo;
	
	@Autowired
	IndustryRepo industryRepo;

	@Override
	public Boolean createIndustry(IndustryDto industryDto) {
		Industry industry = new Industry();
		industry.setName(industryDto.getName());
		List<SubIndustry> subIndustryList = subIndustryRepo.findAllById(industryDto.getSubIndustryId());
		industry.setSubIndustry(subIndustryList);
		industryRepo.save(industry);   
		return true;
	}

	@Override
	public List<Industry> getAllIndustry() {
		List<Industry> industryList = industryRepo.findAll();
		return industryList;
	}

	@Override
	public List<Industry> getAllIndustry(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Industry> industryList = industryRepo.findAll(pageable).getContent();
		return industryList;
	}

	@Override
	public List<Map<String,Object>> getSubIndustryByIndustryId(Long id) {
		Industry industry = industryRepo.findById(id).get();
		List<SubIndustry> subIndustryList = industry.getSubIndustry();
		List<Map<String,Object>>result = new ArrayList<>();
		for(SubIndustry subIndustry:subIndustryList) {
			Map<String,Object>map=new HashMap<>();
			map.put("id", subIndustry.getId());
			map.put("name", subIndustry.getName());
			result.add(map);
		}
		return result;
	}

}
