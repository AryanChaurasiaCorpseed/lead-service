package com.lead.dashboard.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Country {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	
	String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="country_state",joinColumns = {@JoinColumn(name="country_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="country_state_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<State>countryState;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<State> getCountryState() {
		return countryState;
	}

	public void setCountryState(List<State> countryState) {
		this.countryState = countryState;
	}
	
	

}
