package com.lead.dashboard.dto;

import java.util.List;

public class UpdateDepartment {

	Long id;
	String name;
	List<Long>designationIds;
	Long weightValue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getWeightValue() {
		return weightValue;
	}
	public void setWeightValue(Long weightValue) {
		this.weightValue = weightValue;
	}
	public List<Long> getDesignationIds() {
		return designationIds;
	}
	public void setDesignationIds(List<Long> designationIds) {
		this.designationIds = designationIds;
	}
	
	
	
}
