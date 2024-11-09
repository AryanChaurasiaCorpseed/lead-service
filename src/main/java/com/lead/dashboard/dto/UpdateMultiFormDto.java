package com.lead.dashboard.dto;

import java.util.List;

public class UpdateMultiFormDto {

	String status;
	List<Long> id;
	Long currentUserId;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getCurrentUserId() {
		return currentUserId;
	}
	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}
	public List<Long> getId() {
		return id;
	}
	public void setId(List<Long> id) {
		this.id = id;
	}
	
	
	
}
