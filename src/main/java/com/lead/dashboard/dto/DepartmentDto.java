package com.lead.dashboard.dto;

import java.util.List;

public class DepartmentDto {
	
	Long id;
	List<Long>designation;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getDesignation() {
		return designation;
	}
	public void setDesignation(List<Long> designation) {
		this.designation = designation;
	}
	
	

}
