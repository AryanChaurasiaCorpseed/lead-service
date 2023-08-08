package com.lead.dashboard.dto;


import java.util.Date;


public class UpdateLeadDto {


	Long id ;

	String uuid;

	String name;

	String leadName;

	String email;

	String leadDescription;

	String mobileNo;

	String urls;

	Date createDate ;

	Date lastUpdated;

	Date latestStatusChangeDate;

	String source ;

	String PrimaryAddress ;

	boolean isDeleted;

	String city;

	String categoryId;

	String serviceId;

	String industryId;

	String ipAddress;

	String displayStatus;


	int whatsAppStatus;

    Long userId;

	Long statusId;


//======================================//	ALl Getter=================================================


	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getLeadName() {
		return leadName;
	}

	public String getEmail() {
		return email;
	}

	public String getLeadDescription() {
		return leadDescription;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getUrls() {
		return urls;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public Date getLatestStatusChangeDate() {
		return latestStatusChangeDate;
	}

	public String getSource() {
		return source;
	}

	public String getPrimaryAddress() {
		return PrimaryAddress;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public String getCity() {
		return city;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getIndustryId() {
		return industryId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public int getWhatsAppStatus() {
		return whatsAppStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getStatusId() {
		return statusId;
	}

	//	<==============================================ALl Setter++++++++++++++++++++++++++++++++++>


	public void setId(Long id) {
		this.id = id;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLeadDescription(String leadDescription) {
		this.leadDescription = leadDescription;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setLatestStatusChangeDate(Date latestStatusChangeDate) {
		this.latestStatusChangeDate = latestStatusChangeDate;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPrimaryAddress(String primaryAddress) {
		PrimaryAddress = primaryAddress;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public void setWhatsAppStatus(int whatsAppStatus) {
		this.whatsAppStatus = whatsAppStatus;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
}
