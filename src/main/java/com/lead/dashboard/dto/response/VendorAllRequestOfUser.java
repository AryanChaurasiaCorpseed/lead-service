package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorAllRequestOfUser {

    private Long vendorRequestId;
    private String budgetPrice;
    private Long leadId;
    private String leadName;
    private String categoryName;
    private Long categoryId;
    private String subCategoryName;
    private Long subCategoryId;
    private String clientName;
    private String CompanyName;
    private String initialQuotationName;
    private String currentStatus;
    private LocalDate date;
    private String requirementDescription;
    private String clientNumber;
    private String clientEmail;
    private String salesAttachmentImage;

    boolean isView;

    private Long viewedBy;


}
