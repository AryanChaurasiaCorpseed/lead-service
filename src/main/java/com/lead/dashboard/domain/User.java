package com.lead.dashboard.domain;

//import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.opportunity.Opportunities;
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
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
//	private Opportunities opportunities;



//	@ManyToMany
//	@JoinTable(name = "opportunities_id")
//	private Opportunities opportunities;

	public Long getId() {
		return id;
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



	//All Setter


	public void setId(Long id) {
		this.id = id;
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

	
	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	



}

