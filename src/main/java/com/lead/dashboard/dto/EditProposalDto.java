package com.lead.dashboard.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class EditProposalDto {
	
	
	Long id;
	
	Long productId;

	Long primaryContact;
	
	Long secondaryContact;
	
	Boolean isPresent;
	String companyName;//isPresentFalse
	Long companyId;  //isPrsentTrue

	boolean isUnit;
	String unitName; // isUnitFalse
	Long unitId; //ISUNIT TRUE

	String panNo;

	String gstNo;
	String gstType;
	String gstDocuments;
	String companyAge;
	
	//primary
	Boolean isPrimaryAddress=true;
	String primaryTitle;
	String Address;
	String City;
	String State;
	String primaryPinCode;
	String Country;

	//secondary address
	Boolean isSecondaryAddress=true;
	String secondaryTitle;
	String secondaryAddress;
	String secondaryCity;
	String secondaryState;
	String secondaryPinCode;
	String secondaryCountry;
	 
	
	int  govermentfees;
    String govermentCode;
    String govermentGst;
    int professionalFees;
    String professionalCode;
    String profesionalGst;
    int serviceCharge;
    String serviceCode;
    String serviceGst;
    int otherFees;
    String otherCode;
    String otherGst;
	
    
	Long assigneeId; 
	
	
	
	String productType;
	
	List<String>cc;

	
	Long leadId;
	
	@NotBlank
	Date createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Long primaryContact) {
		this.primaryContact = primaryContact;
	}

	public Long getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(Long secondaryContact) {
		this.secondaryContact = secondaryContact;
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

	public boolean isUnit() {
		return isUnit;
	}

	public void setUnit(boolean isUnit) {
		this.isUnit = isUnit;
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

	public String getCompanyAge() {
		return companyAge;
	}

	public void setCompanyAge(String companyAge) {
		this.companyAge = companyAge;
	}

	public int getGovermentfees() {
		return govermentfees;
	}

	public void setGovermentfees(int govermentfees) {
		this.govermentfees = govermentfees;
	}

	public String getGovermentCode() {
		return govermentCode;
	}

	public void setGovermentCode(String govermentCode) {
		this.govermentCode = govermentCode;
	}

	public String getGovermentGst() {
		return govermentGst;
	}

	public void setGovermentGst(String govermentGst) {
		this.govermentGst = govermentGst;
	}

	public int getProfessionalFees() {
		return professionalFees;
	}

	public void setProfessionalFees(int professionalFees) {
		this.professionalFees = professionalFees;
	}

	public String getProfessionalCode() {
		return professionalCode;
	}

	public void setProfessionalCode(String professionalCode) {
		this.professionalCode = professionalCode;
	}

	public String getProfesionalGst() {
		return profesionalGst;
	}

	public void setProfesionalGst(String profesionalGst) {
		this.profesionalGst = profesionalGst;
	}

	public int getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceGst() {
		return serviceGst;
	}

	public void setServiceGst(String serviceGst) {
		this.serviceGst = serviceGst;
	}

	public int getOtherFees() {
		return otherFees;
	}

	public void setOtherFees(int otherFees) {
		this.otherFees = otherFees;
	}

	public String getOtherCode() {
		return otherCode;
	}

	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
	}

	public String getOtherGst() {
		return otherGst;
	}

	public void setOtherGst(String otherGst) {
		this.otherGst = otherGst;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getIsPrimaryAddress() {
		return isPrimaryAddress;
	}

	public void setIsPrimaryAddress(Boolean isPrimaryAddress) {
		this.isPrimaryAddress = isPrimaryAddress;
	}

	public String getPrimaryTitle() {
		return primaryTitle;
	}

	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getPrimaryPinCode() {
		return primaryPinCode;
	}

	public void setPrimaryPinCode(String primaryPinCode) {
		this.primaryPinCode = primaryPinCode;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public Boolean getIsSecondaryAddress() {
		return isSecondaryAddress;
	}

	public void setIsSecondaryAddress(Boolean isSecondaryAddress) {
		this.isSecondaryAddress = isSecondaryAddress;
	}

	public String getSecondaryTitle() {
		return secondaryTitle;
	}

	public void setSecondaryTitle(String secondaryTitle) {
		this.secondaryTitle = secondaryTitle;
	}

	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	public String getSecondaryCity() {
		return secondaryCity;
	}

	public void setSecondaryCity(String secondaryCity) {
		this.secondaryCity = secondaryCity;
	}

	public String getSecondaryState() {
		return secondaryState;
	}

	public void setSecondaryState(String secondaryState) {
		this.secondaryState = secondaryState;
	}

	public String getSecondaryPinCode() {
		return secondaryPinCode;
	}

	public void setSecondaryPinCode(String secondaryPinCode) {
		this.secondaryPinCode = secondaryPinCode;
	}

	public String getSecondaryCountry() {
		return secondaryCountry;
	}

	public void setSecondaryCountry(String secondaryCountry) {
		this.secondaryCountry = secondaryCountry;
	}
	
	
}
