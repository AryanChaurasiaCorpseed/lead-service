package com.lead.dashboard.dto;

import java.util.Date;
import java.util.List;


import jakarta.validation.constraints.NotBlank;

public class CreateNewProposalDto {



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


	//primary
	Boolean isPrimaryAddress=true;
	String primaryTitle;
	String Address;
	String City;
	String State;
	String primaryPinCode;
	String Country;


	List<String>cc;


	Long leadId;
     
	Long fileDataId;
	

	@NotBlank
	Date createDate;
	
	boolean mailSendOrNot;

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

	public Long getFileDataId() {
		return fileDataId;
	}

	public void setFileDataId(Long fileDataId) {
		this.fileDataId = fileDataId;
	}

	public boolean isMailSendOrNot() {
		return mailSendOrNot;
	}

	public void setMailSendOrNot(boolean mailSendOrNot) {
		this.mailSendOrNot = mailSendOrNot;
	}
     
	
	
     
	
	
}
