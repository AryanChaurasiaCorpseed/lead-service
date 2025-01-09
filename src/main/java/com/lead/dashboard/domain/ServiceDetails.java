package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
//@Data
//@Getter
//@Setter
// Send Estimate
public class ServiceDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	private String productName;

	
	@NotBlank
	@ManyToOne
	Product product;
	@ManyToOne
	Contact primaryContact;
	
	@ManyToOne
	Contact secondaryContact;
	
	
	Date estimateData;
	Date createDate;
	
	
	/* --------------- company detail -------------------------------- */
	Boolean isPresent;
	String companyName;//isPresentFalse
	Long companyId;  //isPrsentTrue

	boolean isUnit;
	String unitName; // isUnitFalse
	Long unitId; //ISUNIT TRUE

	String panNo;

	String gstNo;
	String gstType;
	String gstDocuments;
	String companyAge;

	//primary
	Boolean isPrimaryAddress=true;
	String primaryTitle;
	String Address;
	String City;
	String State;
	String primaryPinCode;
	String Country;

	//secondary address
	Boolean isSecondaryAddress=true;
	String secondaryTitle;
	String secondaryAddress;
	String secondaryCity;
	String secondaryState;
	String secondaryPinCode;
	String secondaryCountry;
	
	//hsn details
    boolean hsnSacPrsent;
	String hsnSacDetails;
	String HsnSac;
	String hsnDescription;

	@ManyToOne
	User  assignee;
	Long leadId;
//	Long productId;
	String status;
	
	/* ---------------- product details  ---------------  */
	
	Boolean consultingSale;
	String productType;
	String orderNumber;
	String purchaseDate;
	List<String>cc;
	String  invoiceNote;
	String remarksForOption;
	String documents;

	
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
    boolean isDeleted;
    
    @ManyToOne
    ConsultantByCompany consultantByCompany;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Date getEstimateData() {
		return estimateData;
	}
	public void setEstimateData(Date estimateData) {
		this.estimateData = estimateData;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Boolean getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public boolean isUnit() {
		return isUnit;
	}
	public void setUnit(boolean isUnit) {
		this.isUnit = isUnit;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getUnitId() {
		return unitId;
	}
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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
	public String getGstType() {
		return gstType;
	}
	public void setGstType(String gstType) {
		this.gstType = gstType;
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
	public Boolean getIsPrimaryAddress() {
		return isPrimaryAddress;
	}
	public void setIsPrimaryAddress(Boolean isPrimaryAddress) {
		this.isPrimaryAddress = isPrimaryAddress;
	}
	public String getPrimaryTitle() {
		return primaryTitle;
	}
	public void setPrimaryTitle(String primaryTitle) {
		this.primaryTitle = primaryTitle;
	}
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
	public String getPrimaryPinCode() {
		return primaryPinCode;
	}
	public void setPrimaryPinCode(String primaryPinCode) {
		this.primaryPinCode = primaryPinCode;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public Boolean getIsSecondaryAddress() {
		return isSecondaryAddress;
	}
	public void setIsSecondaryAddress(Boolean isSecondaryAddress) {
		this.isSecondaryAddress = isSecondaryAddress;
	}
	public String getSecondaryTitle() {
		return secondaryTitle;
	}
	public void setSecondaryTitle(String secondaryTitle) {
		this.secondaryTitle = secondaryTitle;
	}
	public String getSecondaryAddress() {
		return secondaryAddress;
	}
	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}
	public String getSecondaryCity() {
		return secondaryCity;
	}
	public void setSecondaryCity(String secondaryCity) {
		this.secondaryCity = secondaryCity;
	}
	public String getSecondaryState() {
		return secondaryState;
	}
	public void setSecondaryState(String secondaryState) {
		this.secondaryState = secondaryState;
	}
	public String getSecondaryPinCode() {
		return secondaryPinCode;
	}
	public void setSecondaryPinCode(String secondaryPinCode) {
		this.secondaryPinCode = secondaryPinCode;
	}
	public String getSecondaryCountry() {
		return secondaryCountry;
	}
	public void setSecondaryCountry(String secondaryCountry) {
		this.secondaryCountry = secondaryCountry;
	}
	
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getDocuments() {
		return documents;
	}
	public void setDocuments(String documents) {
		this.documents = documents;
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
//	public Long getProductId() {
//		return productId;
//	}
//	public void setProductId(Long productId) {
//		this.productId = productId;
//	}
	public ConsultantByCompany getConsultantByCompany() {
		return consultantByCompany;
	}
	public void setConsultantByCompany(ConsultantByCompany consultantByCompany) {
		this.consultantByCompany = consultantByCompany;
	}
	public boolean isHsnSacPrsent() {
		return hsnSacPrsent;
	}
	public String getHsnSacDetails() {
		return hsnSacDetails;
	}
	public String getHsnSac() {
		return HsnSac;
	}
	public String getHsnDescription() {
		return hsnDescription;
	}
	public void setHsnSacPrsent(boolean hsnSacPrsent) {
		this.hsnSacPrsent = hsnSacPrsent;
	}
	public void setHsnSacDetails(String hsnSacDetails) {
		this.hsnSacDetails = hsnSacDetails;
	}
	public void setHsnSac(String hsnSac) {
		HsnSac = hsnSac;
	}
	public void setHsnDescription(String hsnDescription) {
		this.hsnDescription = hsnDescription;
	} 
	
	
	
	
//	@ManyToOne
//	Company companies;
////	String contact;
//	Boolean consultingSale;
//	String productType;
//	String orderNumber;
//	String purchaseDate;
//	List<String>cc;
//	String  invoiceNote;
//	String remarksForOption;

//	String documents;
	
	
//    int  govermentfees;
//    String govermentCode;
//    String govermentGst;
//    int professionalFees;
//    String professionalCode;
//    String profesionalGst;
//    int serviceCharge;
//    String serviceCode;
//    String serviceGst;
//    int otherFees;
//    String otherCode;
//    String otherGst;	
//    boolean isDeleted; 

    
   

	
	
}
