package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorReportRequest {

    private Long userIdBy;
    private List<String> statuses;
    private Date startDate;
    private Date endDate;
    private List<Long> userIds;
}
