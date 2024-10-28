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





}
