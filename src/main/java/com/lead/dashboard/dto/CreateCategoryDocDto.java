package com.lead.dashboard.dto;

import java.util.List;

public class CreateCategoryDocDto {
	
	Long categoryId;
	List<String>docList;
	
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public List<String> getDocList() {
		return docList;
	}
	public void setDocList(List<String> docList) {
		this.docList = docList;
	}
	
	
}
