package com.lead.dashboard.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class LeadDto {
	Long userId;
	String leadName;
	String leadDescription;
	String contacts;
	String urls ;
    Date createDate ; 
    String status="initiated";
//	User createdBy
	Date lastUpdated;
//	User updatedBy;
	Date latestStatusChangeDate;
	String source ;
	String PrimaryAddress ;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}   


}
