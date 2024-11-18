package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorSubCategoryRequest {

    private Long vendorCategoryId;
    private String subCategoryName;
    private int vendorCategoryResearchTat;
    private int vendorCompletionTat;
	public Long getVendorCategoryId() {
		return vendorCategoryId;
	}
	public void setVendorCategoryId(Long vendorCategoryId) {
		this.vendorCategoryId = vendorCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public int getVendorCategoryResearchTat() {
		return vendorCategoryResearchTat;
	}
	public void setVendorCategoryResearchTat(int vendorCategoryResearchTat) {
		this.vendorCategoryResearchTat = vendorCategoryResearchTat;
	}
	public int getVendorCompletionTat() {
		return vendorCompletionTat;
	}
	public void setVendorCompletionTat(int vendorCompletionTat) {
		this.vendorCompletionTat = vendorCompletionTat;
	}
    
    
    
}
