package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
//@Data
public class Notification {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	String message;
	@ManyToOne
	User user;
	boolean isView;
	Date notifyDate;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isView() {
		return isView;
	}
	public void setView(boolean isView) {
		this.isView = isView;
	}
	public Date getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	
	
	
	
}
