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
    private String vendorReferenceFile;
    private String budgetPrice;
    private String vendorSharedPrice;
    private String status;
    private boolean proposalSentStatus;
    private Date createDate;
    private Date updatedDate;
    private String serviceName;
    private String contactNumber;


    private List<VendorUpdateHistoryResponse> updateHistory;

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
        this.serviceName=vendor.getUrlsManagment().getUrlsName();
        this.contactNumber=vendor.getClientMobileNumber();


    }

    public List<VendorUpdateHistoryResponse> getUpdateHistory() {
        return updateHistory;
    }

    public void setUpdateHistory(List<VendorUpdateHistoryResponse> updateHistory) {
        this.updateHistory = updateHistory;
    }
}

