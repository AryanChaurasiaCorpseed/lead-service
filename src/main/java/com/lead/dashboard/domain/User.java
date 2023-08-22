package com.lead.dashboard.domain;

//import com.lead.dashboard.domain.opportunity.Opportunities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@Table(name = "user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String email;

    @NonNull
    private String designation;

    @NonNull
    private String department;

    private String password;

	@NonNull
	private String role;

//	@ManyToMany
//	@JoinTable(name = "opportunities_id")
//	private Opportunities opportunities;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getDesignation() {
		return designation;
	}

	public String getDepartment() {
		return department;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}



	//All Setter


	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

