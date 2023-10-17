package com.lead.dashboard.dto;

import lombok.Data;

@Data
public class AddProductInLead {

	Long productId;
	Long leadId;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	
}
