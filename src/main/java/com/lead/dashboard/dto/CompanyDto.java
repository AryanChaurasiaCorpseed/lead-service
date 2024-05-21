package com.lead.dashboard.dto;

import java.util.List;

public class CompanyDto {
	
	String name;
	String panNo;
	String gstNo;
	String companyAge;
	String Country;
	String State;
	String City;
	String Address;
	List<Long> projectId;
    Long  assigneeId;

	List<Long>leadId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getCompanyAge() {
		return companyAge;
	}
	public void setCompanyAge(String companyAge) {
		this.companyAge = companyAge;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public List<Long> getLeadId() {
		return leadId;
	}
	public void setLeadId(List<Long> leadId) {
		this.leadId = leadId;
	}
	public List<Long> getProjectId() {
		return projectId;
	}
	public void setProjectId(List<Long> projectId) {
		this.projectId = projectId;
	}
	public Long getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
	
	
	

}
