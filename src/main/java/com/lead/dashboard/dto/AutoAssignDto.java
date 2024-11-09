package com.lead.dashboard.dto;

public class AutoAssignDto {
	 Long leadId ;
	 Long updatedById;
	  boolean isAutoSame;
	  String status;
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public Long getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	public boolean isAutoSame() {
		return isAutoSame;
	}
	public void setAutoSame(boolean isAutoSame) {
		this.isAutoSame = isAutoSame;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	  
	  
}
