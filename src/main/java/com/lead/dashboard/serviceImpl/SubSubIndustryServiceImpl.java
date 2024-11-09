package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<SubSubIndustry> getAllSubSubIndustryForPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<SubSubIndustry> subIndus = subSubIndustryRepo.findAll(pageable).getContent();
		return subIndus;
	}

	@Override
	public List<Map<String,Object>> getAllIndustryDataBySubSubIndustryId(Long id) {
		List<IndustryData> industryData = subSubIndustryRepo.findById(id).get().getIndustryDataList();
		 List<Map<String,Object>>result = new ArrayList<>();		 
		for(IndustryData i:industryData) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", i.getId());
			map.put("name", i.getName());
			result.add(map);
		}
		
		return result;
	}

}
