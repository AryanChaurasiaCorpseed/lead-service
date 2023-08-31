package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Data
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


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="client_communication",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="client_communication_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Communication>communication;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="client_service_details",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="client_service_details_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<ServiceDetails>serviceDetails;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emails
	 */
	public String getEmails() {
		return emails;
	}

	/**
	 * @param emails the emails to set
	 */
	public void setEmails(String emails) {
		this.emails = emails;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the communication
	 */
	public List<Communication> getCommunication() {
		return communication;
	}

	/**
	 * @param communication the communication to set
	 */
	public void setCommunication(List<Communication> communication) {
		this.communication = communication;
	}
	
	

}
