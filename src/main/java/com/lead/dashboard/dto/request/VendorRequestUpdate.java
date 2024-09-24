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



       private String serviceName;

       private String clientName;

       private String clientMailId;

       private String requestStatus;

       private String additionalMailId;

       private String comment;

       private boolean proposalSentStatus;

       private String clientContactNumber;

       private String quotationAmount;

       private String quotationFilePath;

       private String externalVendorPrice;

       private String externalVendorFilePath;

       private String internalVendorPrices;

       private String internalVendorFilePath;


    }

