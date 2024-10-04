package com.lead.dashboard.dto;

import java.util.List;

public class ChildLeadDto {
	
	Long leadId;
	List<String>serviceName;
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public List<String> getServiceName() {
		return serviceName;
	}
	public void setServiceName(List<String> serviceName) {
		this.serviceName = serviceName;
	}


}
