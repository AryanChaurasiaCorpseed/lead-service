package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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




    private List<VendorUpdateHistoryAllResponse> vendorUpdateHistoryAllResponseList;


    boolean isView;

    private Long viewedBy;
}
