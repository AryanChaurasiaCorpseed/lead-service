package com.lead.dashboard.dto.response;

import com.lead.dashboard.domain.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorResponse {

    private Long id;
    private String companyName;
    private String contactPersonName;
    private String emailId;
    private String description;
    private String vendorReference;
    private String urlsName;

    public VendorResponse(Vendor vendor) {
        this.id = vendor.getId();
        this.companyName = vendor.getClientCompanyName();
        this.contactPersonName = vendor.getContactPersonName();
        this.emailId = vendor.getClientEmailId();
        this.description = vendor.getRequirementDescription();
        this.vendorReference = vendor.getVendorReferenceFile();
        this.urlsName = vendor.getUrlsManagment() != null ? vendor.getUrlsManagment().getUrlsName() : null;
    }
}
