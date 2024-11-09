package com.lead.dashboard.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.lead.Lead;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Table
@Entity
@Getter
@Setter
@Data
public class LeadHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	
	Date createDate;
	
	@ManyToOne
	@JsonIgnore
	User createdBy;
	
	@ManyToOne
	@JsonIgnore
	User prevUser;
	
	String description;
	
	String eventType;
	
    Long  leadId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLeadId() {
		return leadId;
	}

	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}

	public User getPrevUser() {
		return prevUser;
	}

	public void setPrevUser(User prevUser) {
		this.prevUser = prevUser;
	}
	
	
	

}
