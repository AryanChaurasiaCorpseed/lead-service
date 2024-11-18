package com.lead.dashboard.dto.response;

import com.lead.dashboard.domain.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorResponse {
    private Long id;
    private String requirementDescription;
    private String clientEmailId;
    private String clientCompanyName;
    private String contactPersonName;
    private List<String> vendorReferenceFile;
    private String budgetPrice;
    private String vendorSharedPrice;
    private String status;
    private boolean proposalSentStatus;
    private Date createDate;
    private Date updatedDate;
//    private String serviceName;
//    private String serviceName;
    private String contactNumber;
    private Long leadId;
    private String leadName;
    private Long assigneeId;
    private String assigneeName;

    private String vendorCategoryName;
    private String vendorSubCategoryName;


    private List<VendorUpdateHistoryResponse> updateHistory;

    private List<String> salesAttachmentImage;


    public VendorResponse(Vendor vendor) {
        this.id = vendor.getId();
        this.requirementDescription = vendor.getRequirementDescription();
        this.clientEmailId = vendor.getClientEmailId();
        this.clientCompanyName = vendor.getClientCompanyName();
        this.contactPersonName = vendor.getClientName();
        this.vendorReferenceFile = vendor.getSalesAttachmentReferencePath();
        this.budgetPrice = vendor.getClientBudget();
        this.vendorSharedPrice = vendor.getVendorSharedPrice();
        this.status = vendor.getStatus();
        this.proposalSentStatus = vendor.isProposalSentStatus();
        this.createDate = vendor.getCreateDate();
        this.updatedDate = vendor.getUpdatedDate();
//        this.serviceName=vendor.getUrlsManagment().getUrlsName();
        this.contactNumber=vendor.getClientMobileNumber();
        this.leadId=vendor.getId();
        this.leadName=vendor.getLead().getLeadName();
        this.assigneeId=vendor.getAssignedUser().getId();
        this.assigneeName=vendor.getAssignedUser().getFullName();
        this.salesAttachmentImage=vendor.getSalesAttachmentImage();

//        this.vendorCategoryName=vendor.getVendorCategory().getVendorCategoryName();
//        this.vendorSubCategoryName=vendor.getVendorSubCategory().getVendorSubCategoryName();


    }

    public List<VendorUpdateHistoryResponse> getUpdateHistory() {
        return updateHistory;
    }

    public void setUpdateHistory(List<VendorUpdateHistoryResponse> updateHistory) {
        this.updateHistory = updateHistory;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequirementDescription() {
		return requirementDescription;
	}

	public void setRequirementDescription(String requirementDescription) {
		this.requirementDescription = requirementDescription;
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

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public List<String> getVendorReferenceFile() {
		return vendorReferenceFile;
	}

	public void setVendorReferenceFile(List<String> vendorReferenceFile) {
		this.vendorReferenceFile = vendorReferenceFile;
	}

	public String getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	public String getVendorSharedPrice() {
		return vendorSharedPrice;
	}

	public void setVendorSharedPrice(String vendorSharedPrice) {
		this.vendorSharedPrice = vendorSharedPrice;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public List<String> getSalesAttachmentImage() {
		return salesAttachmentImage;
	}

	public void setSalesAttachmentImage(List<String> salesAttachmentImage) {
		this.salesAttachmentImage = salesAttachmentImage;
	}
    
    
}

