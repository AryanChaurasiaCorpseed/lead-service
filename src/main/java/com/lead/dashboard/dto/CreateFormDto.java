package com.lead.dashboard.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CreateFormDto {
	
	Boolean isPresent;
	String companyName;//isPresentFalse
	Long companyId;  //isPrsentTrue
	
	Boolean isUnit;
	String unitName; // isUnitFalse
	Long unitId; //ISUNIT TRUE
	
	String panNo;
	
	String gstNo;
	String gstType;
	String gstDocuments;
	
	String companyAge;
	
	//primary
	String Address;
	String City;
	String State;
	String Country;
	
	
	//secondary address
	String sAddress;
	String sCity;
	String sState;
	String sCountry;
	
	Long  assigneeId;
	Long leadId;
	String status;
	
	// contact
	boolean isPrimaryContact;
	Long contactId ;
	String contactName;
	String contactEmails;
	String contactNo;
	String contactWhatsappNo;
	
	boolean isSecondaryContact;
	Long sContactId ;
	String sContactName;
	String sContactEmails;
	String sContactNo;
	String sContactWhatsappNo;
	
	public Boolean getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {                           
	}
	public Boolean getIsUnit() {
		return isUnit;
	}
	public void setIsUnit(Boolean isUnit) {
		this.isUnit = isUnit;
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
	public Long getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public String getGstType() {
		return gstType;
	}
	public void setGstType(String gstType) {
		this.gstType = gstType;
	}
	public String getGstDocuments() {
		return gstDocuments;
	}
	public void setGstDocuments(String gstDocuments) {
		this.gstDocuments = gstDocuments;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	public String getsAddress() {
		return sAddress;
	}
	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}
	public String getsCity() {
		return sCity;
	}
	public void setsCity(String sCity) {
		this.sCity = sCity;
	}
	public String getsState() {
		return sState;
	}
	public void setsState(String sState) {
		this.sState = sState;
	}
	public String getsCountry() {
		return sCountry;
	}
	public void setsCountry(String sCountry) {
		this.sCountry = sCountry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isPrimaryContact() {
		return isPrimaryContact;
	}
	public void setPrimaryContact(boolean isPrimaryContact) {
		this.isPrimaryContact = isPrimaryContact;
	}
	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactEmails() {
		return contactEmails;
	}
	public void setContactEmails(String contactEmails) {
		this.contactEmails = contactEmails;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getContactWhatsappNo() {
		return contactWhatsappNo;
	}
	public void setContactWhatsappNo(String contactWhatsappNo) {
		this.contactWhatsappNo = contactWhatsappNo;
	}
	public boolean isSecondaryContact() {
		return isSecondaryContact;
	}
	public void setSecondaryContact(boolean isSecondaryContact) {
		this.isSecondaryContact = isSecondaryContact;
	}
	public Long getsContactId() {
		return sContactId;
	}
	public void setsContactId(Long sContactId) {
		this.sContactId = sContactId;
	}
	public String getsContactName() {
		return sContactName;
	}
	public void setsContactName(String sContactName) {
		this.sContactName = sContactName;
	}
	public String getsContactEmails() {
		return sContactEmails;
	}
	public void setsContactEmails(String sContactEmails) {
		this.sContactEmails = sContactEmails;
	}
	public String getsContactNo() {
		return sContactNo;
	}
	public void setsContactNo(String sContactNo) {
		this.sContactNo = sContactNo;
	}
	public String getsContactWhatsappNo() {
		return sContactWhatsappNo;
	}
	public void setsContactWhatsappNo(String sContactWhatsappNo) {
		this.sContactWhatsappNo = sContactWhatsappNo;
	} 
	
	
	
	
	
	

}
