package com.lead.dashboard.dto;

import jakarta.persistence.Lob;

public class CreateCategory {
	
	String name;
	
	Long userId;
	
	@Lob
	String documents;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
