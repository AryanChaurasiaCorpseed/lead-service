package com.lead.dashboard.dto;

import java.util.List;

import lombok.Data;
@Data
public class RatingDto {
	
	String rating;
    
	Long urlsManagmentId;

	List<Long>ratingsUser;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Long getUrlsManagmentId() {
		return urlsManagmentId;
	}

	public void setUrlsManagmentId(Long urlsManagmentId) {
		this.urlsManagmentId = urlsManagmentId;
	}

	public List<Long> getRatingsUser() {
		return ratingsUser;
	}

	public void setRatingsUser(List<Long> ratingsUser) {
		this.ratingsUser = ratingsUser;
	}
	
}
