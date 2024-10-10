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

    private String vendorCategoryName;
    private String vendorSubCategoryName;


    public VendorUpdateHistoryResponse(VendorUpdateHistory vendorUpdateHistory) {
        this.id = vendorUpdateHistory.getId();
        this.updateDescription = vendorUpdateHistory.getUpdateDescription();
        this.updateDate = vendorUpdateHistory.getUpdateDate();
        this.requestStatus = vendorUpdateHistory.getRequestStatus();
        this.budgetPrice = vendorUpdateHistory.getBudgetPrice();
        this.externalVendorPrice = vendorUpdateHistory.getExternalVendorPrice();
        this.externalVendorFilePath = vendorUpdateHistory.getExternalVendorFilePath();
        this.internalVendorPrices = vendorUpdateHistory.getInternalVendorPrices();
        this.internalVendorFilePath = vendorUpdateHistory.getInternalVendorFilePath();
        this.quotationAmount = vendorUpdateHistory.getQuotationAmount();
        this.quotationFilePath = vendorUpdateHistory.getQuotationFilePath();
        this.vendorCategoryName=vendorUpdateHistory.getVendorCategory().getVendorCategoryName();
        this.vendorSubCategoryName=vendorUpdateHistory.getVendorSubCategory().getVendorSubCategoryName();
    }

}
