package com.lead.dashboard.dto;

import java.util.List;

public class SubIndustryDto {
	
	String name;
	List<Long> subsubIndustryId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getSubsubIndustryId() {
		return subsubIndustryId;
	}
	public void setSubsubIndustryId(List<Long> subsubIndustryId) {
		this.subsubIndustryId = subsubIndustryId;
	}
	
	
}
