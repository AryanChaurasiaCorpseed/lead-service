package com.lead.dashboard.dto;

import lombok.Data;

@Data
public class UpdateOpportunity {

	private Long id;

	private String estimateClose;

	private String confidence;

	private String value;

	private String typePayment;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public void setDescription(String description) {
		this.description = description;
	}


}
