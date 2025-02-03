package com.lead.dashboard.dto;

public class UpdateAmountProductDto {
	

	
	private Long productAmountId;	
	private String name;
	private int fees;
	private String hsnNo;
	private String taxAmount;
	
	public Long getProductAmountId() {
		return productAmountId;
	}
	public String getName() {
		return name;
	}
	public int getFees() {
		return fees;
	}
	public String getHsnNo() {
		return hsnNo;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setProductAmountId(Long productAmountId) {
		this.productAmountId = productAmountId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public void setHsnNo(String hsnNo) {
		this.hsnNo = hsnNo;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	
	
}
