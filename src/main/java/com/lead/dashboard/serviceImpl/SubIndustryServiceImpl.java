package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.domain.SubSubIndustry;
import com.lead.dashboard.dto.SubIndustryDto;
import com.lead.dashboard.dto.SubSubIndustryDto;
import com.lead.dashboard.repository.SubIndustryRepo;
import com.lead.dashboard.repository.SubSubIndustryRepo;
import com.lead.dashboard.service.SubIndustryService;

@Service
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

	@Override
	public List<SubIndustry> getAllSubIndustryForPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<SubIndustry> subIndustryList = subIndustryRepo.findAll(pageable).getContent();
		
		return subIndustryList;
	}

	@Override
	public int getAllSubIndustryCount() {
		List<SubIndustry> subIndustryList = subIndustryRepo.findAll();
		return subIndustryList.size();
	}

	@Override
	public List<Map<String,Object>> getAllSubSubIndustryBySubIndustryId(Long id) {
		List<SubSubIndustry> subsubIndustry = subIndustryRepo.findById(id).get().getSubSubIndustry();
		List<Map<String,Object>>result = new ArrayList<>();
		for(SubSubIndustry s:subsubIndustry) {
			Map<String,Object>map=new HashMap<>();
			map.put("id", s.getId());
			map.put("name", s.getName());
			result.add(map);
		}
		
		return result;
	}


	
}
