package com.lead.dashboard.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
//@Data
public class Project implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id ; 
	
	String name;
	
	String ProjectNo;
	
	String amount;
	
	@ManyToOne
	@JsonBackReference
	ServiceDetails serviceDetails;
	
	@ManyToOne
	@JsonBackReference
	User assignee;
	
	@ManyToOne	
	@JsonBackReference
	Product product;
	
	String status;
	
	
	@ManyToOne
	@JsonBackReference

	Client client;
	
	@OneToOne
	Lead lead;
	
	@ManyToOne
	@JsonBackReference
	Company company;
	
	String progress;
	
	Date createDate;
	
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

	public String getProjectNo() {
		return ProjectNo;
	}

	public void setProjectNo(String projectNo) {
		ProjectNo = projectNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public ServiceDetails getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(ServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}
	
	//address 
	
	
	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getPrimaryPinCode() {
		return primaryPinCode;
	}

	public void setPrimaryPinCode(String primaryPinCode) {
		this.primaryPinCode = primaryPinCode;
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

	public String getSecondaryPinCode() {
		return secondaryPinCode;
	}

	public void setSecondaryPinCode(String secondaryPinCode) {
		this.secondaryPinCode = secondaryPinCode;
	}
    
	
	
	

}
