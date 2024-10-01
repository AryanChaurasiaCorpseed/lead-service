package com.lead.dashboard.dto;

import java.util.List;

public class DeleteMultiLeadDto {

	List<Long>leadId;
	Long updatedById;
	
	public List<Long> getLeadId() {
		return leadId;
	}
	public void setLeadId(List<Long> leadId) {
		this.leadId = leadId;
	}
	public Long getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	
	
}
