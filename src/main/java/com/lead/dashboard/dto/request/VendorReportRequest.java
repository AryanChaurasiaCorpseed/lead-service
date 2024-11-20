package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorReportRequest {

    private Long userIdBy;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> userId;
}
