package com.lead.dashboard.dto;

import java.util.List;


public class AllLeadFilter {
	
	Long userId;
	List<Long> userIdFilter;
	List<Long> statusId;
	String toDate;
	String fromDate;
	

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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Long> getUserIdFilter() {
		return userIdFilter;
	}
	public void setUserIdFilter(List<Long> userIdFilter) {
		this.userIdFilter = userIdFilter;
	}
	
	
}
