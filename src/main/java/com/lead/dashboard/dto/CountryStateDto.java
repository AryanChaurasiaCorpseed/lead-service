package com.lead.dashboard.dto;

import java.util.List;

public class CountryStateDto {
	
	Long countryId;
	List<Long>stateId;
	
	public Long getCountryId() {
		return countryId;
	}
	public List<Long> getStateId() {
		return stateId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public void setStateId(List<Long> stateId) {
		this.stateId = stateId;
	}
	
	

}
