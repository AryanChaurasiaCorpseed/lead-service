package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.product.Product;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "service_details")
@Data
@Getter
@Setter
public class ServiceDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	private String name;
	@NotBlank
//	private String leadName;
	
	@ManyToOne
	Product product;
	
	Date estimateData;
	
//	User clientAdmin;
	String company;
	String contact;
	Boolean consultingSale;
	String productType;
	String orderNumber;
	String purchaseDate;
	List<String>cc;
	String  invoiceNote;
	String remarksForOption;

	@ManyToOne
	Opportunities opportunities;

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
//	public String getLeadName() {
//		return leadName;
//	}
//	public void setLeadName(String leadName) {
//		this.leadName = leadName;
//	}

	
	
	public String getCompany() {
		return company;
	}
	public Date getEstimateData() {
		return estimateData;
	}
	public void setEstimateData(Date estimateData) {
		this.estimateData = estimateData;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Boolean getConsultingSale() {
		return consultingSale;
	}
	public void setConsultingSale(Boolean consultingSale) {
		this.consultingSale = consultingSale;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public String getInvoiceNote() {
		return invoiceNote;
	}
	public void setInvoiceNote(String invoiceNote) {
		this.invoiceNote = invoiceNote;
	}
	public String getRemarksForOption() {
		return remarksForOption;
	}
	public void setRemarksForOption(String remarksForOption) {
		this.remarksForOption = remarksForOption;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Opportunities getOpportunities() {
		return opportunities;
	}
	public void setOpportunities(Opportunities opportunities) {
		this.opportunities = opportunities;
	}
	
	

	
	
}
