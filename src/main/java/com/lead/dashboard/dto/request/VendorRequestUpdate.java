package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

   @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class VendorRequestUpdate {



//       private String serviceName;
//       private String serviceName;

       private String clientName;

       private String clientMailId;

       private String requestStatus;

       private String additionalMailId;

       private String comment;

       private boolean proposalSentStatus;

       private String clientContactNumber;

       private String quotationAmount;

       private String quotationFilePath;

       private String externalVendorPrice;

       private String externalVendorFilePath;

       private String internalVendorPrices;

       private String internalVendorFilePath;


       private Long vendorCategoryId;

       private Long subVendorCategoryId;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMailId() {
		return clientMailId;
	}

	public void setClientMailId(String clientMailId) {
		this.clientMailId = clientMailId;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAdditionalMailId() {
		return additionalMailId;
	}

	public void setAdditionalMailId(String additionalMailId) {
		this.additionalMailId = additionalMailId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isProposalSentStatus() {
		return proposalSentStatus;
	}

	public void setProposalSentStatus(boolean proposalSentStatus) {
		this.proposalSentStatus = proposalSentStatus;
	}

	public String getClientContactNumber() {
		return clientContactNumber;
	}

	public void setClientContactNumber(String clientContactNumber) {
		this.clientContactNumber = clientContactNumber;
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

	public Long getVendorCategoryId() {
		return vendorCategoryId;
	}

	public void setVendorCategoryId(Long vendorCategoryId) {
		this.vendorCategoryId = vendorCategoryId;
	}

	public Long getSubVendorCategoryId() {
		return subVendorCategoryId;
	}

	public void setSubVendorCategoryId(Long subVendorCategoryId) {
		this.subVendorCategoryId = subVendorCategoryId;
	}
       
       


    }


