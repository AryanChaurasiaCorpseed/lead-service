package com.lead.dashboard.dto;

public class TatAndDescDto {
	
	 Long productId;
     Long tatValue=0l;
     String tatType;
     String remarks;
     
	
	public long getTatValue() {
		return tatValue;
	}
	public void setTatValue(long tatValue) {
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
