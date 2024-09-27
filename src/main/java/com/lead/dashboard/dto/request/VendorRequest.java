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

    private String serviceName;

    private String companyName;

    private String status;

    private String clientMailId;

    private String clientName;

    private String clientMobileNumber;

    private String saleTeamAttachmentReference;

    private Long assignedUserId;

    private String clientBudgetPrice;



}
