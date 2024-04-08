package com.lead.dashboard.domain;

//import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@Table(name = "user")
public class User implements Serializable{
	

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
    
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role",joinColumns = {@JoinColumn(name="user_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="user_role_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
    private List<Role>userRole;
	@NonNull
	private List<String> role;	
	boolean isDeleted;
	
    // NEW  fIELD
    boolean isManager;
	String epfNo;
	String aadharCard;
	String employeeId;	
	@ManyToOne
	User managers;
	int expInMonth;
	int expInYear;
    Date dateOfJoining;
    String type;// internal ,external
    String fatherName;
    String fatherOccupation;
    String fatherContactNo;
    String motherName;
    String motherOccupation;
    String motherContactNo;
    String spouseName;
    String spouseContactNo;
    String nationality;
    String language;
    String emergencyNumber;
    String panNumber;
    //Document photo
    String permanentAddress;
    String residentialAddress;
    String birthCertificate;
    String matric;
    String intermediate;
    String voterId;
    String heCertificate;//highest education certificate
    boolean isHrHeadApproval;
    String ManagerApproval="pending";// approved,disapproved,pending
    
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_product",joinColumns = {@JoinColumn(name="user_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="user_product_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Product>userProduct;
	
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

	public User getManagers() {
		return managers;
	}

	public void setManagers(User managers) {
		this.managers = managers;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherOccupation() {
		return fatherOccupation;
	}

	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}

	public String getFatherContactNo() {
		return fatherContactNo;
	}

	public void setFatherContactNo(String fatherContactNo) {
		this.fatherContactNo = fatherContactNo;
	}
	
	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getMotherContactNo() {
		return motherContactNo;
	}

	public void setMotherContactNo(String motherContactNo) {
		this.motherContactNo = motherContactNo;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseContactNo() {
		return spouseContactNo;
	}

	public void setSpouseContactNo(String spouseContactNo) {
		this.spouseContactNo = spouseContactNo;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmergencyNumber() {
		return emergencyNumber;
	}

	public void setEmergencyNumber(String emergencyNumber) {
		this.emergencyNumber = emergencyNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getBirthCertificate() {
		return birthCertificate;
	}

	public void setBirthCertificate(String birthCertificate) {
		this.birthCertificate = birthCertificate;
	}

	public String getMatric() {
		return matric;
	}

	public void setMatric(String matric) {
		this.matric = matric;
	}

	public String getIntermediate() {
		return intermediate;
	}

	public void setIntermediate(String intermediate) {
		this.intermediate = intermediate;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getHeCertificate() {
		return heCertificate;
	}

	public void setHeCertificate(String heCertificate) {
		this.heCertificate = heCertificate;
	}

	public boolean isHrHeadApproval() {
		return isHrHeadApproval;
	}

	public void setHrHeadApproval(boolean isHrHeadApproval) {
		this.isHrHeadApproval = isHrHeadApproval;
	}

	public String getManagerApproval() {
		return ManagerApproval;
	}

	public void setManagerApproval(String managerApproval) {
		ManagerApproval = managerApproval;
	}

    

}

