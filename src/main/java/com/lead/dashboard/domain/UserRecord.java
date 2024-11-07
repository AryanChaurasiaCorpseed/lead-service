package com.lead.dashboard.domain;

import java.util.Date;

import com.lead.dashboard.domain.lead.Lead;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class UserRecord {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id ; 
	
	
	@ManyToOne
	User currentUser;
	
//	Date currentDate;
	String event;
	
	@ManyToOne
	User updatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
}
