package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.opportunity.Opportunities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String name;
	String emails;
	String contactNo;
	boolean deleteStatus =false;
	boolean  isPrimary;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="client_communication",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="client_communication_id"+ "",referencedColumnName = "id",nullable=true,unique=false)})
	@JsonIgnore
	List<Communication>communication;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="client_service_details",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="client_service_details_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
	@JsonIgnore
	List<ServiceDetails>serviceDetails;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name="client_opportunities",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
//	inverseJoinColumns = {@JoinColumn(name="client_opportunities_id"+ "",referencedColumnName = "id",nullable=true)})
//	private List<Opportunities> opportunities;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public boolean isDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public List<Communication> getCommunication() {
		return communication;
	}
	public void setCommunication(List<Communication> communication) {
		this.communication = communication;
	}
	public List<ServiceDetails> getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(List<ServiceDetails> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	

}
