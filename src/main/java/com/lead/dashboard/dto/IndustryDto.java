package com.lead.dashboard.dto;

import java.util.List;

public class IndustryDto {
	
	String name;
	List<Long> subIndustryId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getSubIndustryId() {
		return subIndustryId;
	}
	public void setSubIndustryId(List<Long> subIndustryId) {
		this.subIndustryId = subIndustryId;
	}
	
	
}
 