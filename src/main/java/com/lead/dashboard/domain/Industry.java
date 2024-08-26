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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@Data 
public class Industry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String name;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="industry_sub_industry",joinColumns = {@JoinColumn(name="industry_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="industry_sub_industry_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<SubIndustry>subIndustry;

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

	public List<SubIndustry> getSubIndustry() {
		return subIndustry;
	}

	public void setSubIndustry(List<SubIndustry> subIndustry) {
		this.subIndustry = subIndustry;
	}
	
	
}
