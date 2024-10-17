package com.lead.dashboard.dto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

public class UpdateCompanyDto {

	 List<Long> companyId;
	 Long currentUserId;
	 Long assigneeId;
	 

	 
	public List<Long> getCompanyId() {
		return companyId;
	}
	public void setCompanyId(List<Long> companyId) {
		this.companyId = companyId;
	}
	public Long getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}
	public Long getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
	 
	 
	
}
