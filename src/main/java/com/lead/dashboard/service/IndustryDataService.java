package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.IndustryData;

@Service
public interface IndustryDataService {


	Boolean createIndustryData(String name);

	List<IndustryData> getAllIndustryData();

	List<IndustryData> getAllIndustryDataForPage(int page, int size);

}
