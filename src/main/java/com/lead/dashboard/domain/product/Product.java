package com.lead.dashboard.domain.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.ProductAmount;
import com.lead.dashboard.domain.ProductDocuments;
import com.lead.dashboard.domain.Stages;
import com.lead.dashboard.domain.User;

@Entity
@Table
//@Getter 
//@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Date createdDate; 
    
    
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
    
    
    @ManyToOne
    User createdBy;
    boolean isDeleted=false;
	List<String> documents;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="product_stage",joinColumns = {@JoinColumn(name="product_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="product_stage_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Stages>productStage;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="product_prod_documents",joinColumns = {@JoinColumn(name="product_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="product_prod_documents_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<ProductDocuments>productDoc;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="product_prod_amount",joinColumns = {@JoinColumn(name="product_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="product_prod_amount_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<ProductAmount>productAmount;
	
	
	String tatValue;
	String tatType;
	String description;
	
	
	
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<String> getDocuments() {
		return documents;
	}
	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}
	public List<Stages> getProductStage() {
		return productStage;
	}
	public void setProductStage(List<Stages> productStage) {
		this.productStage = productStage;
	}
	public List<ProductDocuments> getProductDoc() {
		return productDoc;
	}
	public void setProductDoc(List<ProductDocuments> productDoc) {
		this.productDoc = productDoc;
	}
	public List<ProductAmount> getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(List<ProductAmount> productAmount) {
		this.productAmount = productAmount;
	}
	public String getTatValue() {
		return tatValue;
	}
	public void setTatValue(String tatValue) {
		this.tatValue = tatValue;
	}
	public String getTatType() {
		return tatType;
	}
	public void setTatType(String tatType) {
		this.tatType = tatType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

	
	
	


	




    

}
