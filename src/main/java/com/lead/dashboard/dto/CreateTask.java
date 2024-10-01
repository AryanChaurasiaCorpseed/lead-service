package com.lead.dashboard.dto;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;


@Data
public class CreateTask {
	Long leadId;
	String name;
	String description;
	Long assignedById;
	Date expectedDate;
	Long statusId;
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public Long getAssigneeId() {
//		return assigneeId;
//	}
//	public void setAssigneeId(Long assigneeId) {
//		this.assigneeId = assigneeId;
//	}
	public Long getAssignedById() {
		return assignedById;
	}
	public void setAssignedById(Long assignedById) {
		this.assignedById = assignedById;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	
	

}
