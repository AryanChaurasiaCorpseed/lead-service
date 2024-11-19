package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorAllRequestOfUser {

    private Long vendorRequestId;
    private String budgetPrice;
    private Long leadId;
    private String leadName;
    private String categoryName;
    private Long categoryId;
    private String subCategoryName;
    private Long subCategoryId;
    private String clientName;
    private String CompanyName;
    private String initialQuotationName;
    private String currentStatus;
    private LocalDate date;
    private String requirementDescription;
    private String clientNumber;
    private String clientEmail;
    private List<String> salesAttachmentImage;

    boolean isView;

    private String viewedBy;


    private Long assigneeId;
    private String assigneeName;
	public Long getVendorRequestId() {
		return vendorRequestId;
	}
	public void setVendorRequestId(Long vendorRequestId) {
		this.vendorRequestId = vendorRequestId;
	}
	public String getBudgetPrice() {
		return budgetPrice;
	}
	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getInitialQuotationName() {
		return initialQuotationName;
	}
	public void setInitialQuotationName(String initialQuotationName) {
		this.initialQuotationName = initialQuotationName;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getRequirementDescription() {
		return requirementDescription;
	}
	public void setRequirementDescription(String requirementDescription) {
		this.requirementDescription = requirementDescription;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public List<String> getSalesAttachmentImage() {
		return salesAttachmentImage;
	}
	public void setSalesAttachmentImage(List<String> salesAttachmentImage) {
		this.salesAttachmentImage = salesAttachmentImage;
	}
	public boolean isView() {
		return isView;
	}
	public void setView(boolean isView) {
		this.isView = isView;
	}


	public Long getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getViewedBy() {
		return viewedBy;
	}
	public void setViewedBy(String viewedBy) {
		this.viewedBy = viewedBy;
	}
    
    


}