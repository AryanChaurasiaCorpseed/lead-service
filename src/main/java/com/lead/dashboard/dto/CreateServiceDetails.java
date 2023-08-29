package com.lead.dashboard.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateServiceDetails {

	private String name;
	@NotBlank
	String estimateData;
	String company;
	String contact;
	Boolean consultingSale;
	String productType;
	String orderNumber;
	String purchaseDate;
	List<String>cc;
	String  invoiceNote;
	String remarksForOption;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEstimateData() {
		return estimateData;
	}
	public void setEstimateData(String estimateData) {
		this.estimateData = estimateData;
	}
	public String getCompany() {
		return company;
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
	
	
	
}
