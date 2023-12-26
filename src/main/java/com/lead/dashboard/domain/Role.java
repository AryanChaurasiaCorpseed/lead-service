package com.lead.dashboard.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	boolean isUnique;//never be deleted


	private String name;
	
	List<String>accessedNode;

	boolean isDeleted ; 

	public Role() {

	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public boolean isDeleted() {
		return isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}



	public List<String> getAccessedNode() {
		return accessedNode;
	}



	public void setAccessedNode(List<String> accessedNode) {
		this.accessedNode = accessedNode;
	}


	public boolean isUnique() {
		return isUnique;
	}


	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	
	

	// getters and setters

}
