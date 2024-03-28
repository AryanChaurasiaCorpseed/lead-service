package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.opportunity.Opportunities;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

// Send Estimate
public class ServiceDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	private String name;
	private String ServiceName;
	
	
	
	@ManyToOne
	Client client;
	@NotBlank
	@ManyToOne
	Product product;	
	Date estimateData;
	String company;
	@ManyToOne
	Company companies;
	String contact;
	Boolean consultingSale;
	String productType;
	String orderNumber;
	String purchaseDate;
	Long quantity;
	List<String>cc;
	String  invoiceNote;
	String remarksForOption;
	@ManyToOne
	Opportunities opportunities;
	
    double  govermentfees;
    String govermentCode;
    double govermentGst;
    double professionalFees;
    String professionalCode;
    double profesionalGst;
    double serviceCharge;
    String serviceCode;
    double serviceGst;
    double otherFees;
    String otherCode;
    double otherGst;	
    boolean isDeleted;
    double totalamount;
    
    boolean isProjectCreated; //Project is Created or Not
    

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="estimate_payment_data",joinColumns = {@JoinColumn(name="estimate_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="estimate_payment_data_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<PaymentData>estimatePaymentData;


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


	public String getServiceName() {
		return ServiceName;
	}


	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
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


	public Company getCompanies() {
		return companies;
	}


	public void setCompanies(Company companies) {
		this.companies = companies;
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


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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


	public Opportunities getOpportunities() {
		return opportunities;
	}


	public void setOpportunities(Opportunities opportunities) {
		this.opportunities = opportunities;
	}


	public double getGovermentfees() {
		return govermentfees;
	}


	public void setGovermentfees(double govermentfees) {
		this.govermentfees = govermentfees;
	}


	public String getGovermentCode() {
		return govermentCode;
	}


	public void setGovermentCode(String govermentCode) {
		this.govermentCode = govermentCode;
	}


	public double getGovermentGst() {
		return govermentGst;
	}


	public void setGovermentGst(double govermentGst) {
		this.govermentGst = govermentGst;
	}


	public double getProfessionalFees() {
		return professionalFees;
	}


	public void setProfessionalFees(double professionalFees) {
		this.professionalFees = professionalFees;
	}


	public String getProfessionalCode() {
		return professionalCode;
	}


	public void setProfessionalCode(String professionalCode) {
		this.professionalCode = professionalCode;
	}


	public double getProfesionalGst() {
		return profesionalGst;
	}


	public void setProfesionalGst(double profesionalGst) {
		this.profesionalGst = profesionalGst;
	}


	public double getServiceCharge() {
		return serviceCharge;
	}


	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}


	public String getServiceCode() {
		return serviceCode;
	}


	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}


	public double getServiceGst() {
		return serviceGst;
	}


	public void setServiceGst(double serviceGst) {
		this.serviceGst = serviceGst;
	}


	public double getOtherFees() {
		return otherFees;
	}


	public void setOtherFees(double otherFees) {
		this.otherFees = otherFees;
	}


	public String getOtherCode() {
		return otherCode;
	}


	public void setOtherCode(String otherCode) {
		this.otherCode = otherCode;
	}


	public double getOtherGst() {
		return otherGst;
	}


	public void setOtherGst(double otherGst) {
		this.otherGst = otherGst;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public boolean isProjectCreated() {
		return isProjectCreated;
	}


	public void setProjectCreated(boolean isProjectCreated) {
		this.isProjectCreated = isProjectCreated;
	}


	public List<PaymentData> getEstimatePaymentData() {
		return estimatePaymentData;
	}


	public void setEstimatePaymentData(List<PaymentData> estimatePaymentData) {
		this.estimatePaymentData = estimatePaymentData;
	}


	public double getTotalamount() {
		return totalamount;
	}


	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
    
    
	
	
       

    
   
	
}
