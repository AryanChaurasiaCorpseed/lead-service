package com.lead.dashboard.dto;

import java.util.List;


public class AllLeadFilter {
	List<Long> userId;
	List<Long> statusId;
	String toDate;
	String fromDate;
	
	public List<Long> getUserId() {
		return userId;
	}
	public void setUserId(List<Long> userId) {
		this.userId = userId;
	}
	public List<Long> getStatusId() {
		return statusId;
	}
	public void setStatusId(List<Long> statusId) {
		this.statusId = statusId;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	
}
