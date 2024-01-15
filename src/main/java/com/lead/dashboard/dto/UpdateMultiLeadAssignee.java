package com.lead.dashboard.dto;

import java.util.List;

public class UpdateMultiLeadAssignee {
	List<Long>leadIds;
	Long statusId;
	Long assigneId;
	
	public List<Long> getLeadIds() {
		return leadIds;
	}
	public void setLeadIds(List<Long> leadIds) {
		this.leadIds = leadIds;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getAssigneId() {
		return assigneId;
	}
	public void setAssigneId(Long assigneId) {
		this.assigneId = assigneId;
	}
	
	
	
	

}
