package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubSubIndustryDto;
import com.lead.dashboard.repository.IndustryDataRepo;
import com.lead.dashboard.repository.SubSubIndustryRepo;
import com.lead.dashboard.service.SubSubIndustryService;

@Service
public class SubSubIndustryServiceImpl implements SubSubIndustryService {

	@Autowired
	SubSubIndustryRepo subSubIndustryRepo;
	
	@Autowired
	IndustryDataRepo industryDataRepo;
	@Override
	public Boolean createSubSubIndustry(SubSubIndustryDto subSubIndustryDto) {
		Boolean flag=false;
		SubSubIndustry subSubIndustry = new SubSubIndustry();
		subSubIndustry.setName(subSubIndustryDto.getName());
		subSubIndustryDto.getIndustryDataId();
		List<IndustryData>indusList=industryDataRepo.findAllByIdIn(subSubIndustryDto.getIndustryDataId());
		subSubIndustry.setIndustryDataList(indusList);
		subSubIndustryRepo.save(subSubIndustry);
		flag=true;
		return flag;
	}

	@Override
	public List<SubSubIndustry> getAllSubSubIndustry() {
		List<SubSubIndustry> subIndus = subSubIndustryRepo.findAll();
		return subIndus;
	}

}
