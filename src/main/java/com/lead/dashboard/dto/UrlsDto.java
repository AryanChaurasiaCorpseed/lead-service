package com.lead.dashboard.dto;

import java.util.List;


public class UrlsDto {
	 String name;
	 boolean isQuality;
	 List<Long> urlSlug;
	 
	 
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
	public List<Long> getUrlSlug() {
		return urlSlug;
	}
	public void setUrlSlug(List<Long> urlSlug) {
		this.urlSlug = urlSlug;
	}
	
	 
}
