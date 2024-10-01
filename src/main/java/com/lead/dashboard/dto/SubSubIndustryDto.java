package com.lead.dashboard.dto;

import java.util.List;

public class SubSubIndustryDto {
	
	String name;
	List<Long> industryDataId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getIndustryDataId() {
		return industryDataId;
	}
	public void setIndustryDataId(List<Long> industryDataId) {
		this.industryDataId = industryDataId;
	}
	
	

}
