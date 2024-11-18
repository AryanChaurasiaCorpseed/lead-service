package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateHistoryAllResponse {

    private Long id;
    private String requestStatus;
    private String updateDescription;
    private Date updateDate;
    private String budgetPrice;
    private boolean proposalSentStatus;
    private String externalVendorPrice;

    private String externalVendorFilePath;

    private String internalVendorPrices;

    private String internalVendorFilePath;

    private String quotationAmount;

    private String quotationFilePath;

    private String vendorCategoryName;
    private String vendorSubCategoryName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getUpdateDescription() {
		return updateDescription;
	}
	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getBudgetPrice() {
		return budgetPrice;
	}
	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}
	public boolean isProposalSentStatus() {
		return proposalSentStatus;
	}
	public void setProposalSentStatus(boolean proposalSentStatus) {
		this.proposalSentStatus = proposalSentStatus;
	}
	public String getExternalVendorPrice() {
		return externalVendorPrice;
	}
	public void setExternalVendorPrice(String externalVendorPrice) {
		this.externalVendorPrice = externalVendorPrice;
	}
	public String getExternalVendorFilePath() {
		return externalVendorFilePath;
	}
	public void setExternalVendorFilePath(String externalVendorFilePath) {
		this.externalVendorFilePath = externalVendorFilePath;
	}
	public String getInternalVendorPrices() {
		return internalVendorPrices;
	}
	public void setInternalVendorPrices(String internalVendorPrices) {
		this.internalVendorPrices = internalVendorPrices;
	}
	public String getInternalVendorFilePath() {
		return internalVendorFilePath;
	}
	public void setInternalVendorFilePath(String internalVendorFilePath) {
		this.internalVendorFilePath = internalVendorFilePath;
	}
	public String getQuotationAmount() {
		return quotationAmount;
	}
	public void setQuotationAmount(String quotationAmount) {
		this.quotationAmount = quotationAmount;
	}
	public String getQuotationFilePath() {
		return quotationFilePath;
	}
	public void setQuotationFilePath(String quotationFilePath) {
		this.quotationFilePath = quotationFilePath;
	}
	public String getVendorCategoryName() {
		return vendorCategoryName;
	}
	public void setVendorCategoryName(String vendorCategoryName) {
		this.vendorCategoryName = vendorCategoryName;
	}
	public String getVendorSubCategoryName() {
		return vendorSubCategoryName;
	}
	public void setVendorSubCategoryName(String vendorSubCategoryName) {
		this.vendorSubCategoryName = vendorSubCategoryName;
	}
    
    



}
