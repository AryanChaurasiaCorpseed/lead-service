package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateHistoryAllResponse {

    private Long id;
    private String requestStatus;
    private String updateDescription;
    private Date updateDate;
    private String budgetPrice;
    private boolean proposalSentStatus;
    private String externalVendorPrice;

    private String externalVendorFilePath;

    private String internalVendorPrices;

    private String internalVendorFilePath;

    private String quotationAmount;

    private String quotationFilePath;

    private String vendorCategoryName;
    private String vendorSubCategoryName;



}
