package com.lead.dashboard.dto;

import java.util.List;

import lombok.NonNull;

public class UpdateUser {
	
	private Long id;
	

    
    private String fullName;

    @NonNull
    private String email;

    @NonNull
    private String designation;

    @NonNull
    private String department;


	@NonNull
	private List<String> role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
	
	

}
