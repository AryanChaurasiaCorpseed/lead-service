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
import jakarta.persistence.Table;

@Table
@Entity
public class State {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="state_city",joinColumns = {@JoinColumn(name="state_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="state_city_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<City>stateCity;
	
	
	
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
	public List<City> getStateCity() {
		return stateCity;
	}
	public void setStateCity(List<City> stateCity) {
		this.stateCity = stateCity;
	}
	
	
}
