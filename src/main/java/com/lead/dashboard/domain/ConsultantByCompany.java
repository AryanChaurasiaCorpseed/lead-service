package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Table
@Entity
public class ConsultantByCompany {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String name;
	String originalContact;
	String originalEmail;
	String address;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalContact() {
		return originalContact;
	}
	public void setOriginalContact(String originalContact) {
		this.originalContact = originalContact;
	}
	public String getOriginalEmail() {
		return originalEmail;
	}
	public void setOriginalEmail(String originalEmail) {
		this.originalEmail = originalEmail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
