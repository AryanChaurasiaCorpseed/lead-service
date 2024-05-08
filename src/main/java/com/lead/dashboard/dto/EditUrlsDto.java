package com.lead.dashboard.dto;

import java.util.List;

public class EditUrlsDto {
	 Long urlsId;
	 String name;
	 boolean isQuality;
	 List<Long> slugId;
	public Long getUrlsId() {
		return urlsId;
	}
	public void setUrlsId(Long urlsId) {
		this.urlsId = urlsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isQuality() {
		return isQuality;
	}
	public void setQuality(boolean isQuality) {
		this.isQuality = isQuality;
	}
	public List<Long> getSlugId() {
		return slugId;
	}
	public void setSlugId(List<Long> slugId) {
		this.slugId = slugId;
	}
	 
	 

}
