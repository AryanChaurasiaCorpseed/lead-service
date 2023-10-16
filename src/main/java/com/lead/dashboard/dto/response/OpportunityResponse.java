package com.lead.dashboard.dto.response;


import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.opportunity.OpportunityPaymentTerm;
import com.lead.dashboard.domain.opportunity.OpportunityStatus;
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

    private OpportunityStatus statusId;

    private OpportunityPaymentTerm payTermId;

    private Client clientId;

    private User userId;


}
