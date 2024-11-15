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
public class VendorReportResponse {

    private Long id ;

    private String generateByPersonName;

    private String assignedByPersonName;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate completionDate;

    private String clientName;

    private String currentStatus;

    private String subCategoryName;

    private int vendorCategoryResearchTat = 0;

    private int vendorCompletionTat =0;

    private int completionDays;

    private int tatDaysLeft;

    private int overDueTat;





}
