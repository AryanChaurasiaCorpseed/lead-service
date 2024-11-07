package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.IndustryData;
import com.lead.dashboard.repository.IndustryDataRepo;
import com.lead.dashboard.service.IndustryDataService;
@Service
public class IndustryDataServiceImpl implements IndustryDataService{

	@Autowired
	IndustryDataRepo industryDataRepo;
	@Override
	public Boolean createIndustryData(String name) {
	
		Boolean flag=false;
		IndustryData industryData=new IndustryData();
		industryData.setName(name);
	    industryDataRepo.save(industryData);
	    flag=true;
		return flag;
	}
	@Override
	public List<IndustryData> getAllIndustryData() {
		List<IndustryData> res = industryDataRepo.findAll();
		return res;
	}
	@Override
	public List<IndustryData> getAllIndustryDataForPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<IndustryData> res = industryDataRepo.findAll(pageable).getContent();
		return res;
	}

}
