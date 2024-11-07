	package com.lead.dashboard.dto;

	import java.util.List;

import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	public class CreateFormDto {

		Boolean isPresent;
		String companyName;//isPresentFalse
		Long companyId;  //isPrsentTrue

		boolean isUnit;
		String unitName; // isUnitFalse
		Long unitId; // ISUNIT TRUE

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
		String sAddress;
		String sCity;
		String sState;
		String secondaryPinCode;
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
		String primaryDesignation;

		boolean isSecondaryContact;
		Long sContactId ;
		String sContactName;
		String sContactEmails;
		String sContactNo;
		String sContactWhatsappNo;
		String secondaryDesignation;

		Long updatedBy;

		String addedByInOldCrm;
		
		String amount;
		
		//==== Industry
		Long industryId;
		Long subIndustryId;
		Long subsubIndustryId;
		List<Long> industrydataId;



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
		public String getCountry() {
			return Country;
		}
		public void setCountry(String country) {
			Country = country;
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
		public Long getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(Long updatedBy) {
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
		public String getAddedByInOldCrm() {
			return addedByInOldCrm;
		}
		public void setAddedByInOldCrm(String addedByInOldCrm) {
			this.addedByInOldCrm = addedByInOldCrm;
		}
		public Boolean getIsPrimaryAddress() {
			return isPrimaryAddress;
		}
		public void setIsPrimaryAddress(Boolean isPrimaryAddress) {
			this.isPrimaryAddress = isPrimaryAddress;
		}
		public Boolean getIsSecondaryAddress() {
			return isSecondaryAddress;
		}
		public void setIsSecondaryAddress(Boolean isSecondaryAddress) {
			this.isSecondaryAddress = isSecondaryAddress;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
		public String getPrimaryDesignation() {
			return primaryDesignation;
		}
		public void setPrimaryDesignation(String primaryDesignation) {
			this.primaryDesignation = primaryDesignation;
		}
		public String getSecondaryDesignation() {
			return secondaryDesignation;
		}
		public void setSecondaryDesignation(String secondaryDesignation) {
			this.secondaryDesignation = secondaryDesignation;
		}
		public String getPrimaryTitle() {
			return primaryTitle;
		}
		public void setPrimaryTitle(String primaryTitle) {
			this.primaryTitle = primaryTitle;
		}
		public String getSecondaryTitle() {
			return secondaryTitle;
		}
		public void setSecondaryTitle(String secondaryTitle) {
			this.secondaryTitle = secondaryTitle;
		}
		public Long getIndustryId() {
			return industryId;
		}
		public void setIndustryId(Long industryId) {
			this.industryId = industryId;
		}
		public Long getSubIndustryId() {
			return subIndustryId;
		}
		public void setSubIndustryId(Long subIndustryId) {
			this.subIndustryId = subIndustryId;
		}
		public Long getSubsubIndustryId() {
			return subsubIndustryId;
		}
		public void setSubsubIndustryId(Long subsubIndustryId) {
			this.subsubIndustryId = subsubIndustryId;
		}
		public List<Long> getIndustrydataId() {
			return industrydataId;
		}
		public void setIndustrydataId(List<Long> industrydataId) {
			this.industrydataId = industrydataId;
		}
		
		
	
	}
