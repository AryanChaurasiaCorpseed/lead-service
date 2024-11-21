package com.lead.dashboard.dto;

import java.util.List;

public class SimilarSlugDto {

	
	 Long id;
	 List<Long> urlSlugIds;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getUrlSlugIds() {
		return urlSlugIds;
	}
	public void setUrlSlugIds(List<Long> urlSlugIds) {
		this.urlSlugIds = urlSlugIds;
	}
	 
	 
}
