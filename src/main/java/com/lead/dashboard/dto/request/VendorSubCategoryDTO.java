package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorSubCategoryDTO {

    private Long id;
    private String vendorSubCategoryName;
    private String vendorCategoryName;
    private Long addedBy;
    private Date createdAt;
    private int vendorCategoryResearchTat ;
    private int vendorCompletionTat ;
    private LocalDate date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVendorSubCategoryName() {
		return vendorSubCategoryName;
	}
	public void setVendorSubCategoryName(String vendorSubCategoryName) {
		this.vendorSubCategoryName = vendorSubCategoryName;
	}
	public String getVendorCategoryName() {
		return vendorCategoryName;
	}
	public void setVendorCategoryName(String vendorCategoryName) {
		this.vendorCategoryName = vendorCategoryName;
	}
	public Long getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(Long addedBy) {
		this.addedBy = addedBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

    


}
