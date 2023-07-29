package com.lead.dashboard.dto;

import java.util.Date;

public class UpdateLeadDto {

	Long id;
	String leadName;
	String leadDescription;
	String contacts;
	String urls ;
    Date createDate ; 
//	User createdBy
	Date lastUpdated;
//	User updatedBy;
	Date latestStatusChangeDate;
	String source ;
	String PrimaryAddress ;
	Long statusId;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the leadName
	 */
	public String getLeadName() {
		return leadName;
	}
	/**
	 * @param leadName the leadName to set
	 */
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	/**
	 * @return the leadDescription
	 */
	public String getLeadDescription() {
		return leadDescription;
	}
	/**
	 * @param leadDescription the leadDescription to set
	 */
	public void setLeadDescription(String leadDescription) {
		this.leadDescription = leadDescription;
	}
	/**
	 * @return the contacts
	 */
	public String getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	/**
	 * @return the urls
	 */
	public String getUrls() {
		return urls;
	}
	/**
	 * @param urls the urls to set
	 */
	public void setUrls(String urls) {
		this.urls = urls;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the lastUpdated
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * @return the latestStatusChangeDate
	 */
	public Date getLatestStatusChangeDate() {
		return latestStatusChangeDate;
	}
	/**
	 * @param latestStatusChangeDate the latestStatusChangeDate to set
	 */
	public void setLatestStatusChangeDate(Date latestStatusChangeDate) {
		this.latestStatusChangeDate = latestStatusChangeDate;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the primaryAddress
	 */
	public String getPrimaryAddress() {
		return PrimaryAddress;
	}
	/**
	 * @param primaryAddress the primaryAddress to set
	 */
	public void setPrimaryAddress(String primaryAddress) {
		PrimaryAddress = primaryAddress;
	}
	/**
	 * @return the statusId
	 */
	public Long getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}   
	

}
