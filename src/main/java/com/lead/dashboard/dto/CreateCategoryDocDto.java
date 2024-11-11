package com.lead.dashboard.dto;

import java.util.List;

public class CreateCategoryDocDto {
	
	long categoryId;
	List<String>docList;
	
	

	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public List<String> getDocList() {
		return docList;
	}
	public void setDocList(List<String> docList) {
		this.docList = docList;
	}
	
	
}
