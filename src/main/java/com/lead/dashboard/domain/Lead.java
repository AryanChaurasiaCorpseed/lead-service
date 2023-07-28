package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lead_u")
@Data
@Getter
@Setter
public class Lead {
p
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ; 
	String leadName;
	String leadDescription;
	String contacts;
	String urls ;
    Date createDate ; 
    @OneToOne
	User createdBy;
	Date lastUpdated;
	@OneToOne
	User updatedBy;
	Date latestStatusChangeDate;
	String source ;
	String PrimaryAddress ;
	boolean isDeleted;

//	  Long id;
//	   String name ; 
//	   Contact contacts;
//	   String description ; 
//	   @OneToOne
//	   Status status_id;
//	   String urls;
//	   Date createDate ; 
//	   User createdBy
//	   Date updated;
//	   User updatedBy;
//	   Date LatestStatus Change Date;
//	   Source source ;
//	   String PrimaryAddress ;   

	
	
	
	
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
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the updatedBy
	 */
	public User getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
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
	 * @return the isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}
	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
