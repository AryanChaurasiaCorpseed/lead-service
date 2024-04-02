package com.lead.dashboard.domain;

//import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.opportunity.Opportunities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;
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
    boolean isManager;
	String epfNo;
	String aadharCard;
	String employeeId;	
	@ManyToOne
	User managerName;
	int expInMonth;
	int expInYear;
    Date dateOfJoining;
    String type;// internal ,external
    
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role",joinColumns = {@JoinColumn(name="user_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="user_role_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
    private List<Role>userRole;
	@NonNull
	private List<String> role;	
	boolean isDeleted;

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

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getEpfNo() {
		return epfNo;
	}

	public void setEpfNo(String epfNo) {
		this.epfNo = epfNo;
	}

	public String getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public int getExpInMonth() {
		return expInMonth;
	}

	public void setExpInMonth(int expInMonth) {
		this.expInMonth = expInMonth;
	}

	public int getExpInYear() {
		return expInYear;
	}

	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}

	public List<Role> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<Role> userRole) {
		this.userRole = userRole;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User getManagerName() {
		return managerName;
	}

	public void setManagerName(User managerName) {
		this.managerName = managerName;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	

}

