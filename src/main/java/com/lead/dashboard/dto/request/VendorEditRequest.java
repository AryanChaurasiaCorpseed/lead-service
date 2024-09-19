package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorEditRequest {
    private String clientCompanyName;
    private String clientEmailId;
    private String contactPersonName;
    private String contactNumber;
}
