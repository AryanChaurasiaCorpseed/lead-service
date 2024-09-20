package com.lead.dashboard.dto.response;

import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorHistoryUpdate {

    private Long id;
    private String updateDescription;
    private String status;
    private String vendorSharedPrice;
    private String quotationAttachmentPath;
    private String quotationAmount;
    private boolean proposalSentStatus;
    private Date updateDate;

    public VendorHistoryUpdate(VendorUpdateHistory history) {
        this.id = history.getId();
        this.updateDescription = history.getUpdateDescription();
        this.status = history.getRequestStatus();
        this.vendorSharedPrice = history.getVendorSharedPrice();
        this.quotationAttachmentPath = history.getQuotationAttachmentPath();
        this.quotationAmount = history.getQuotationAmount();
        this.proposalSentStatus = history.isProposalSentStatus();
        this.updateDate = history.getUpdateDate();
    }
}
