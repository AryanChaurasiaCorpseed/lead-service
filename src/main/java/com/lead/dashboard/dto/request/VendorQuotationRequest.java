package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorQuotationRequest {


   private String serviceName;

   private String clientName;

   private String clientMailId;

   private String requestStatus;

   private String additionalMailId;

   private String comment;

   private String attachmentPath;

   private boolean proposalSentStatus;

}
