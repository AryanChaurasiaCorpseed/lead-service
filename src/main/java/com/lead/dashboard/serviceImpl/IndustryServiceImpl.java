package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
