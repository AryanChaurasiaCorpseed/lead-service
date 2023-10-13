package com.lead.dashboard.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpportunityResponse {


    private String estimateClose;

    private String confidence;

    private String value;

    private String typePayment;

    private String status;

}
