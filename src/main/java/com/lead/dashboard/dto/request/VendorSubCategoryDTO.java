package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorSubCategoryDTO {

    private Long id;
    private String vendorSubCategoryName;
    private String vendorCategoryName;
    private Long addedBy;
    private Date createdAt;
    private int vendorCategoryResearchTat ;
    private int vendorCompletionTat ;
    private LocalDate date;



}
