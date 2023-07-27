package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ; 
	String leadName;
	String leadDescription;
	
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

}
