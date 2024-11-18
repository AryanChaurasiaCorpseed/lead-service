package com.lead.dashboard.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequest {


    private String description;

//    private String serviceName;
//    private String serviceName;


    private Long vendorCategoryId;

    private Long subVendorCategoryId;


    private String companyName;

    private String status;

    private String clientMailId;

    private String clientName;

    private String clientMobileNumber;

    private List<String> salesAttachmentReferencePath;

    private Long assignedUserId;
//    private Long assignedUserId;

    private String clientBudgetPrice;

    private List<String> salesAttachmentImage;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClientMailId() {
		return clientMailId;
	}

	public void setClientMailId(String clientMailId) {
		this.clientMailId = clientMailId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public List<String> getSalesAttachmentReferencePath() {
		return salesAttachmentReferencePath;
	}

	public void setSalesAttachmentReferencePath(List<String> salesAttachmentReferencePath) {
		this.salesAttachmentReferencePath = salesAttachmentReferencePath;
	}

	public Long getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public String getClientBudgetPrice() {
		return clientBudgetPrice;
	}

	public void setClientBudgetPrice(String clientBudgetPrice) {
		this.clientBudgetPrice = clientBudgetPrice;
	}

	public List<String> getSalesAttachmentImage() {
		return salesAttachmentImage;
	}

	public void setSalesAttachmentImage(List<String> salesAttachmentImage) {
		this.salesAttachmentImage = salesAttachmentImage;
	}


    



}
