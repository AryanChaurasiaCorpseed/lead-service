package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorAllResponse {

    private Long id;
    private String clientEmailId;
    private String clientCompanyName;
    private String clientName;
    private String clientMobileNumber;
    private String requirementDescription;
    private String status;
    private boolean proposalSentStatus;
    private Long leadId;
    private String leadName;
    private String budgetPrice;
    private String serviceName;
    private Long assigneeId;
    private String assigneeName;

    private Long vendorCategoryId;
    private String vendorCategoryName;

    private Long vendorSubCategoryId;
    private String vendorSubCategoryName;
    private String raiseBy;

    private List<String> salesAttachmentImage;

	private LocalDate receivedDate;

	private int subCategoryTatDays;


    private List<VendorUpdateHistoryAllResponse> vendorUpdateHistoryAllResponseList;


    boolean isView;

    private String viewedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientEmailId() {
		return clientEmailId;
	}

	public void setClientEmailId(String clientEmailId) {
		this.clientEmailId = clientEmailId;
	}

	public String getClientCompanyName() {
		return clientCompanyName;
	}

	public void setClientCompanyName(String clientCompanyName) {
		this.clientCompanyName = clientCompanyName;
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

	public String getRequirementDescription() {
		return requirementDescription;
	}

	public void setRequirementDescription(String requirementDescription) {
		this.requirementDescription = requirementDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isProposalSentStatus() {
		return proposalSentStatus;
	}

	public void setProposalSentStatus(boolean proposalSentStatus) {
		this.proposalSentStatus = proposalSentStatus;
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

	public String getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
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

	public Long getVendorCategoryId() {
		return vendorCategoryId;
	}

	public void setVendorCategoryId(Long vendorCategoryId) {
		this.vendorCategoryId = vendorCategoryId;
	}

	public String getVendorCategoryName() {
		return vendorCategoryName;
	}

	public void setVendorCategoryName(String vendorCategoryName) {
		this.vendorCategoryName = vendorCategoryName;
	}

	public Long getVendorSubCategoryId() {
		return vendorSubCategoryId;
	}

	public void setVendorSubCategoryId(Long vendorSubCategoryId) {
		this.vendorSubCategoryId = vendorSubCategoryId;
	}

	public String getVendorSubCategoryName() {
		return vendorSubCategoryName;
	}

	public void setVendorSubCategoryName(String vendorSubCategoryName) {
		this.vendorSubCategoryName = vendorSubCategoryName;
	}

	public String getRaiseBy() {
		return raiseBy;
	}

	public void setRaiseBy(String raiseBy) {
		this.raiseBy = raiseBy;
	}

	public List<String> getSalesAttachmentImage() {
		return salesAttachmentImage;
	}

	public void setSalesAttachmentImage(List<String> salesAttachmentImage) {
		this.salesAttachmentImage = salesAttachmentImage;
	}

	public List<VendorUpdateHistoryAllResponse> getVendorUpdateHistoryAllResponseList() {
		return vendorUpdateHistoryAllResponseList;
	}

	public void setVendorUpdateHistoryAllResponseList(
			List<VendorUpdateHistoryAllResponse> vendorUpdateHistoryAllResponseList) {
		this.vendorUpdateHistoryAllResponseList = vendorUpdateHistoryAllResponseList;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}


    
    
    
    
}
