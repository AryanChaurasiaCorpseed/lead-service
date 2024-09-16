package com.lead.dashboard.dto.response;

import com.lead.dashboard.domain.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorResponse {

    private String companyName;
    private String contactPersonName;
    private String emailId;
    private String description;
    private String vendorReference;
    private String urlsName;
    private String estimatePrice;
    private String estimatePriceByVendorTeam;
    private Date createDate;
    private String status;

    public VendorResponse(Vendor vendor) {

        this.companyName = vendor.getClientCompanyName();
        this.contactPersonName = vendor.getContactPersonName();
        this.emailId = vendor.getClientEmailId();
        this.description = vendor.getRequirementDescription();
        this.vendorReference = vendor.getVendorReferenceFile();
        this.urlsName = vendor.getUrlsManagment() != null ? vendor.getUrlsManagment().getUrlsName() : null;
        this.estimatePrice = vendor.getEstimatePrice();
        this.estimatePriceByVendorTeam = vendor.getEstimatePriceByVendorTeam();
        this.createDate = vendor.getCreateDate();
        this.status = vendor.getStatus();
    }
}
