package com.lead.dashboard.dto.response;

import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateHistoryResponse {
    private Long id;
    private String updateDescription;
    private Date updateDate;
    private String requestStatus;
    private String budgetPrice;
    private String externalVendorPrice;

    private String externalVendorFilePath;

    private String internalVendorPrices;

    private String internalVendorFilePath;

    private String quotationAmount;

    private String quotationFilePath;


    public VendorUpdateHistoryResponse(VendorUpdateHistory vendorUpdateHistory) {
    }
}
