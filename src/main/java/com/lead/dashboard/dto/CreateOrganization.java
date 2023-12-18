package com.lead.dashboard.dto;

import com.lead.dashboard.domain.Organization.Plans;

import lombok.Data;

@Data
public class CreateOrganization {
	
	String planType;
	String organizationName;
	boolean isInternalOrExternal;
	String IndustryName;//==== convert in domain
	Long teamSize;
	Long phoneNo;
	Long userId;
	Long plansId;
	
	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public boolean isInternalOrExternal() {
		return isInternalOrExternal;
	}
	public void setInternalOrExternal(boolean isInternalOrExternal) {
		this.isInternalOrExternal = isInternalOrExternal;
	}
	public String getIndustryName() {
		return IndustryName;
	}
	public void setIndustryName(String industryName) {
		IndustryName = industryName;
	}
	public Long getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(Long teamSize) {
		this.teamSize = teamSize;
	}
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	

}
