package com.lead.dashboard.dto;

import java.util.List;

public class UpdateRatingDto {
	Long ratingId;
	Long serviceId;
	List<Long> userId;
	String name;
	
	public Long getRatingId() {
		return ratingId;
	}
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getUserId() {
		return userId;
	}
	public void setUserId(List<Long> userId) {
		this.userId = userId;
	}
	
	
}
