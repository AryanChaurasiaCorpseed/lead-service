package com.lead.dashboard.dto;

public class StageDto {
	
	String name;
	String noOfDays;
	int stageNo;
	int transferPercent;
	int pricePercent;
	Long productId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getStageNo() {
		return stageNo;
	}
	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}
	public int getTransferPercent() {
		return transferPercent;
	}
	public void setTransferPercent(int transferPercent) {
		this.transferPercent = transferPercent;
	}
	public int getPricePercent() {
		return pricePercent;
	}
	public void setPricePercent(int pricePercent) {
		this.pricePercent = pricePercent;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}
	
	
}
