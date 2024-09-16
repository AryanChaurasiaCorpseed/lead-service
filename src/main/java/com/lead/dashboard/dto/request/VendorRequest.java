package com.lead.dashboard.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorRequest {


    private String description;

    private String concernPersonMailId;

    private Long urlId;

    private String companyName;

    private String contactPersonName;

    private String vendorReferenceFile;
}
