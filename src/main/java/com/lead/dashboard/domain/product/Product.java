package com.lead.dashboard.domain.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import com.lead.dashboard.domain.User;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Date createdDate; 
    double  govermentfees;
    String govermentCode;
    double govermentGst;
    double professionalFees;
    String professionalCode;
    double profesionalGst;
    double  serviceCharge;
    String serviceCode;
    double serviceGst;
    double otherFees;
    String otherCode;
    double otherGst;     
    @ManyToOne
    User createdBy;
    boolean isDeleted;
    double totalAmount;
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
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
    

}
