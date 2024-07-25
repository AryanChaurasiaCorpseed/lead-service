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
//@Data
public class Company {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	String name;
	String panNo;
	String gstNo;
	String companyAge;
	String Country;
	String industry;
	String State;
	String City;
	String Address;
	@ManyToMany(cascade = CascadeType.ALL)
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
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public List<Lead> getCompanyLead() {
		return companyLead;
	}
	public void setCompanyLead(List<Lead> companyLead) {
		this.companyLead = companyLead;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public List<Project> getCompanyProject() {
		return companyProject;
	}
	public void setCompanyProject(List<Project> companyProject) {
		this.companyProject = companyProject;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
	
	

}
