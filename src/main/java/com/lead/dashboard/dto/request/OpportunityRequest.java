package com.lead.dashboard.dto.request;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.opportunity.OpportunityPaymentTerm;
import com.lead.dashboard.domain.opportunity.OpportunityStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpportunityRequest {


    @NotNull
    private String estimateClose;

    private String confidence;

    private String value;

    private String typePayment;

    private OpportunityStatus statusId;

    private OpportunityPaymentTerm payTermId;

    private Client clientId;

    private User userId;

}
