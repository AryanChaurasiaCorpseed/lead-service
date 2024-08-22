package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubIndustryDto;
import com.lead.dashboard.dto.SubSubIndustryDto;
import com.lead.dashboard.repository.SubIndustryRepo;
import com.lead.dashboard.repository.SubSubIndustryRepo;
import com.lead.dashboard.service.SubIndustryService;

public class SubIndustryServiceImpl implements SubIndustryService{
	
	@Autowired
	SubIndustryRepo subIndustryRepo;

	@Autowired
	SubSubIndustryRepo subSubIndustryRepo;
	@Override
	public Boolean createSubIndustry(SubIndustryDto subIndustryDto) {
		Boolean flag=false;
		SubIndustry subIndustry = new SubIndustry();
		subIndustry.setName(subIndustryDto.getName());
		List<SubSubIndustry>subSubIndustry=subSubIndustryRepo.findAllByIdIn(subIndustryDto.getSubsubIndustryId());
	    subIndustry.setSubSubIndustry(subSubIndustry);
	    subIndustryRepo.save(subIndustry);
	    flag=true;
		return flag;
	}

	@Override
	public List<SubIndustry> getAllSubIndustry() {
		List<SubIndustry> subIndustryList = subIndustryRepo.findAll();
		return subIndustryList;
	}

}
