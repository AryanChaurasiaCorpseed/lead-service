package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

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


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="client_communication",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="client_communication_id"+ "",referencedColumnName = "id",nullable=true,unique=false)})
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
	@JoinTable(name="client_opportunities",joinColumns = {@JoinColumn(name="client_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="client_opportunities_id"+ "",referencedColumnName = "id",nullable=true)})
	private Opportunities opportunities;


}
