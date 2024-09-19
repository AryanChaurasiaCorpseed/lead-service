package com.lead.dashboard.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequest {


    private String description;

    private String concernPersonMailId;

    private String serviceName;

    private String companyName;

    private String status;

    private String contactNumber;

    private String contactPersonName;

    private String vendorReferenceFile;

    private Long assignedUserId;

    private String budgetPrice;

    private String vendorSharedPrice;

    private String quotationAttachmentPath;
    private String quotationAmount;
    private boolean proposalSentStatus;




}
