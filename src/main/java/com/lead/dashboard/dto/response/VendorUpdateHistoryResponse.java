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
    private String vendorSharedPrice;

    public VendorUpdateHistoryResponse(VendorUpdateHistory updateHistory) {
        this.id = updateHistory.getId();
        this.updateDescription = updateHistory.getUpdateDescription();
        this.updateDate = updateHistory.getUpdateDate();
        this.requestStatus = updateHistory.getRequestStatus();
        this.budgetPrice = updateHistory.getBudgetPrice();
        this.vendorSharedPrice = updateHistory.getVendorSharedPrice();
    }
}
