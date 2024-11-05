package com.lead.dashboard.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class CreateProposalDto {
	
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
	
	
	
	
}
