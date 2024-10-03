package com.lead.dashboard.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Slug {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;		
	boolean isPlantSetup;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name="slug_child",joinColumns = {@JoinColumn(name="slug_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="slug_child_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Slug>slugList;
	
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
	public boolean isPlantSetup() {
		return isPlantSetup;
	}
	public void setPlantSetup(boolean isPlantSetup) {
		this.isPlantSetup = isPlantSetup;
	}
	public List<Slug> getSlugList() {
		return slugList;
	}
	public void setSlugList(List<Slug> slugList) {
		this.slugList = slugList;
	}
	
	
}
