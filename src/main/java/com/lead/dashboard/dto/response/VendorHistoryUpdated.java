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
public class VendorHistoryUpdated {

    private Long id;
    private String updateDescription;
    private String status;
    private String vendorSharedPrice;
    private boolean proposalSentStatus;
    private Date updateDate;

    private String externalVendorPrice;

    private String externalVendorFilePath;

    private String internalVendorPrices;

    private String internalVendorFilePath;

    private String quotationAmount;

    private String quotationFilePath;



}
