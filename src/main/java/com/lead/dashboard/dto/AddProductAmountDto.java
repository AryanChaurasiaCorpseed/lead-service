package com.lead.dashboard.dto;

public class AddProductAmountDto {

	
	private Long  productId;
	private Long categoryId;
	private Long userId;
	
	String name;
	int fees;
	String hsnNo;
	String taxAmount;
	
	
//	
//	int  govermentfees;
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public String getHsnNo() {
		return hsnNo;
	}
	public void setHsnNo(String hsnNo) {
		this.hsnNo = hsnNo;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	
    
}
