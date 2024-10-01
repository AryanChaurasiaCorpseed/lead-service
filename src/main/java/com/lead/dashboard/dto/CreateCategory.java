package com.lead.dashboard.dto;

import java.util.List;

import jakarta.persistence.Lob;

public class CreateCategory {
	
	String name;
	
	Long userId;
	
	@Lob
	List<String> documents;

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

	public List<String> getDocuments() {
		return documents;
	}

	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}

	
	
	

}
