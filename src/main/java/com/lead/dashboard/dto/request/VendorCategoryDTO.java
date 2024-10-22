package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorCategoryDTO {
    private Long id;
    private String vendorCategoryName;
    private boolean isEnable;
    private LocalDate date;
    private Long addedBy;
    private String addedByUserName;
}