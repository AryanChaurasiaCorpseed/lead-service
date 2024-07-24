package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;
import lombok.NonNull;

public class UpdateUserByHr {

	private Long id;
	private String userName;
	@NonNull
	private String email;
	@NonNull
	private String designation;
	@NonNull
	private String department;
	
    Long designationId;
    Long departmentId;
	
	@NonNull
	private List<String> role;
	private boolean isManager;
	private String epfNo;
	private String aadharCard;
	private String employeeId;	
	private Long managerId;
	private int expInMonth;
	private int expInYear;
	private Date dateOfJoining;
	private String type;
	private String fatherName;
	private String fatherOccupation;
	private String fatherContactNo;
	private String motherName;
	private String motherOccupation;
	private String motherContactNo;
	private String spouseName;
	private String spouseContactNo;
	private String nationality;
	private String language;
	private String emergencyNumber;
	private String panNumber;
	private String permanentAddress;
	private String residentialAddress;
	
	private int lockerSize;
	private boolean isBackupTeam;
	private boolean isMaster;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
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
	public int getLockerSize() {
		return lockerSize;
	}
	public void setLockerSize(int lockerSize) {
		this.lockerSize = lockerSize;
	}
	public boolean isBackupTeam() {
		return isBackupTeam;
	}
	public void setBackupTeam(boolean isBackupTeam) {
		this.isBackupTeam = isBackupTeam;
	}
	public boolean isMaster() {
		return isMaster;
	}
	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
    
	



}
