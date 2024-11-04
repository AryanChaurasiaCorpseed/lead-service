package com.lead.dashboard.dto;

public class TatAndDescDto {
	
	 Long productId;
     String tatValue;
     String tatType;
     String remarks;
     
	
	
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
     
     
}
