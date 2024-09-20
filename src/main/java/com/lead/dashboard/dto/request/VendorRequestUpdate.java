package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

   @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class VendorRequestUpdate {


        private String description;

        private String serviceName;

        private String status;

        private String vendorReferenceFile;

        private String vendorSharedPrice;


    }


