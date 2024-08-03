package com.lead.dashboard.domain;

import java.util.List;

import com.lead.dashboard.domain.lead.Lead;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class CompanyForm {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	
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
	String primaryPinCode;
	String Country;
	
	
	//secondary address
	String sAddress;
	String sCity;
	String sState;
	String secondaryPinCode;
	String sCountry;
	
	Long  assigneeId;
	String status;
	@ManyToOne
	Lead lead;
	
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
	
    @ManyToOne
    User updatedBy;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		this.companyId = companyId;
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

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSAddress() {
		return sAddress;
	}

	public void setSAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public String getSCity() {
		return sCity;
	}

	public void setSCity(String sCity) {
		this.sCity = sCity;
	}

	public String getSState() {
		return sState;
	}

	public void setSState(String sState) {
		this.sState = sState;
	}

	public String getSCountry() {
		return sCountry;
	}

	public void setSCountry(String sCountry) {
		this.sCountry = sCountry;
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

	public Long getSContactId() {
		return sContactId;
	}

	public void setSContactId(Long sContactId) {
		this.sContactId = sContactId;
	}

	public String getSContactName() {
		return sContactName;
	}

	public void setSContactName(String sContactName) {
		this.sContactName = sContactName;
	}

	public String getSContactEmails() {
		return sContactEmails;
	}

	public void setSContactEmails(String sContactEmails) {
		this.sContactEmails = sContactEmails;
	}

	public String getSContactNo() {
		return sContactNo;
	}

	public void setSContactNo(String sContactNo) {
		this.sContactNo = sContactNo;
	}

	public String getSContactWhatsappNo() {
		return sContactWhatsappNo;
	}

	public void setSContactWhatsappNo(String sContactWhatsappNo) {
		this.sContactWhatsappNo = sContactWhatsappNo;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getPrimaryPinCode() {
		return primaryPinCode;
	}

	public void setPrimaryPinCode(String primaryPinCode) {
		this.primaryPinCode = primaryPinCode;
	}

	public String getSecondaryPinCode() {
		return secondaryPinCode;
	}

	public void setSecondaryPinCode(String secondaryPinCode) {
		this.secondaryPinCode = secondaryPinCode;
	}  
	 
	
	
	
	
}
