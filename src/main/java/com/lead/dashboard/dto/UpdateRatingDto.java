package com.lead.dashboard.dto;

import java.util.List;

public class UpdateRatingDto {
	Long ratingId;
	Long urlsManagmentId;
	List<Long> ratingsUser;
	String rating;
	
	
	public Long getRatingId() {
		return ratingId;
	}
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}

	 
}
