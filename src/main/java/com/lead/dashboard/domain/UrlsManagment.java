package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Table
@Entity
@Data
public class UrlsManagment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String urlsName;
	boolean isQuality;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrlsName() {
		return urlsName;
	}
	public void setUrlsName(String urlsName) {
		this.urlsName = urlsName;
	}
	public boolean isQuality() {
		return isQuality;
	}
	public void setQuality(boolean isQuality) {
		this.isQuality = isQuality;
	}
	
	
	
	
	
	
	
	
	  
	
}
