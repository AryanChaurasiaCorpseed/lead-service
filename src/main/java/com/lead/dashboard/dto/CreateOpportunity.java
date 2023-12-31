package com.lead.dashboard.dto;

import lombok.Data;

@Data
public class CreateOpportunity {

    private String estimateClose;
    
    private Long serviceId;

    private String confidence;

    private String value;

    private String typePayment;
    
    private String description;
    
    private Long userId;
    
    private Long statusId;

	public String getEstimateClose() {
		return estimateClose;
	}

	public void setEstimateClose(String estimateClose) {
		this.estimateClose = estimateClose;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(String typePayment) {
		this.typePayment = typePayment;
	}

	public String getDescription() {
		return description;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	
	
	
    
    

}
