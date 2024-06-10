package com.lead.dashboard.domain.lead;

import java.util.Date;

import com.lead.dashboard.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
@Table
@Entity
public class Remark {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

	private String message;
	
	String images;

	@ManyToOne
	User updatedBy;
	
	Date latestUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getLatestUpdated() {
		return latestUpdated;
	}

	public void setLatestUpdated(Date latestUpdated) {
		this.latestUpdated = latestUpdated;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	
	

}
