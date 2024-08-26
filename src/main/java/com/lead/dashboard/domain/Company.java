package com.lead.dashboard.domain;

import java.util.List;

import com.lead.dashboard.domain.lead.Lead;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;




@Table
@Entity
//@Getter
//@Setter
@Data
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	String name;
	String panNo;
	//Gst
	String gstType;
	String gstNo;
	String gstDocuments;
	
	// Address
	
	String companyAge;
	String industry;

	//primary
	String Address;
	String City;
	String State;
	String Country;
	String primaryPinCode;	
	
	//secondary address
	String sAddress;
	String sCity;
	String sState;
	String sCountry;
	String secondaryPinCode;

	
	@ManyToOne
	Contact primaryContact;
	
	@ManyToOne
	Contact secondaryContact;
	
	@ManyToMany
	@JoinTable(name="company_lead",joinColumns = {@JoinColumn(name="company_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="company_lead_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Lead>companyLead;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="company_project",joinColumns = {@JoinColumn(name="company_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="company_project_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Project>companyProject;
	
	@ManyToOne                                                                                                               
	User assignee;
	String status;
	boolean isParent;
	@ManyToOne
	Company parent;
	
	boolean isDeleted;
	
	

	public String getPrimaryPinCode() {
		return primaryPinCode;
	}

	public void setPrimaryPinCode(String primaryPinCode) {
		this.primaryPinCode = primaryPinCode;
	}

	public String getSecondaryPinCode() {
		return secondaryPinCode;
	}

	public void setSecondaryPinCode(String secondaryPinCode) {
		this.secondaryPinCode = secondaryPinCode;
	}

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

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getGstType() {
		return gstType;
	}

	public void setGstType(String gstType) {
		this.gstType = gstType;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getGstDocuments() {
		return gstDocuments;
	}

	public void setGstDocuments(String gstDocuments) {
		this.gstDocuments = gstDocuments;
	}

	public String getCompanyAge() {
		return companyAge;
	}

	public void setCompanyAge(String companyAge) {
		this.companyAge = companyAge;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Contact getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Contact primaryContact) {
		this.primaryContact = primaryContact;
	}

	public Contact getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(Contact secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public List<Lead> getCompanyLead() {
		return companyLead;
	}

	public void setCompanyLead(List<Lead> companyLead) {
		this.companyLead = companyLead;
	}

	public List<Project> getCompanyProject() {
		return companyProject;
	}

	public void setCompanyProject(List<Project> companyProject) {
		this.companyProject = companyProject;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Company getParent() {
		return parent;
	}

	public void setParent(Company parent) {
		this.parent = parent;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getSAddress() {
		return sAddress;
	}

	public void setSAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public String getSCity() {
		return sCity;
	}

	public void setSCity(String sCity) {
		this.sCity = sCity;
	}

	public String getSState() {
		return sState;
	}

	public void setSState(String sState) {
		this.sState = sState;
	}

	public String getSCountry() {
		return sCountry;
	}

	public void setSCountry(String sCountry) {
		this.sCountry = sCountry;
	}
    
	
	
	
	
	

}
