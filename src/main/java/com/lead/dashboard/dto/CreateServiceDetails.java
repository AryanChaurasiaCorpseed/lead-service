package com.lead.dashboard.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.domain.Company;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//@Data
public class CreateServiceDetails {

	private String name;
	@NotBlank
	Date estimateData;
	Long productId;
	//company
    Long companyId;
	String company;
	String panNo;
	String gstNo;
	String companyAge;
	String Country;
	String State;
	String City;
	String Address;
	Long quantity;
//	@ManyToOne
//	Company companyName;
	
	int  govermentfees;
    String govermentCode;
    String govermentGst;
    int professionalFees;
    String professionalCode;
    String profesionalGst;
    int serviceCharge;
    String serviceCode;
    String serviceGst;
    int otherFees;
    String otherCode;
    String otherGst;
    
	
	String contact;
	Boolean consultingSale;
	String productType;
	String orderNumber;
	String purchaseDate;
	List<String>cc;
	String  invoiceNote;
	String remarksForOption;
	Long leadId;
	String clientName;
	String contactNo ;
    String email ;
	Long clientId;
	
	
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Date getEstimateData() {
		return estimateData;
	}
	public void setEstimateData(Date estimateData) {
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	public int getGovermentfees() {
		return govermentfees;
	}
	public void setGovermentfees(int govermentfees) {
		this.govermentfees = govermentfees;
	}
	public String getGovermentCode() {
		return govermentCode;
	}
	public void setGovermentCode(String govermentCode) {
		this.govermentCode = govermentCode;
	}
	public String getGovermentGst() {
		return govermentGst;
	}
	public void setGovermentGst(String govermentGst) {
		this.govermentGst = govermentGst;
	}
	public int getProfessionalFees() {
		return professionalFees;
	}
	public void setProfessionalFees(int professionalFees) {
		this.professionalFees = professionalFees;
	}
	public String getProfessionalCode() {
		return professionalCode;
	}
	public void setProfessionalCode(String professionalCode) {
		this.professionalCode = professionalCode;
	}
	public String getProfesionalGst() {
		return profesionalGst;
	}
	public void setProfesionalGst(String profesionalGst) {
		this.profesionalGst = profesionalGst;
	}
	public int getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(int serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceGst() {
		return serviceGst;
	}
	public void setServiceGst(String serviceGst) {
		this.serviceGst = serviceGst;
	}
	public int getOtherFees() {
		return otherFees;
	}
	public void setOtherFees(int otherFees) {
		this.otherFees = otherFees;
	}
	public String getOtherCode() {
		return otherCode;
	}
	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
	}
	public String getOtherGst() {
		return otherGst;
	}
	public void setOtherGst(String otherGst) {
		this.otherGst = otherGst;
	}
	
	
	
	
	
	
}
