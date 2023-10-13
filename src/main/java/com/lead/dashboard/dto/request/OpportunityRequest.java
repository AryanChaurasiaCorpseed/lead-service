package com.lead.dashboard.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpportunityRequest {


    private String estimateClose;

    private String confidence;

    private String value;

    private String typePayment;

    private String status;

}
