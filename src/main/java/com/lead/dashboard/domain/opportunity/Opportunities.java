package com.lead.dashboard.domain.opportunity;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
public class Opportunities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    private String estimateClose;

    private String confidence;

    private String value;

    private String typePayment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OpportunityStatus opportunityStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_term_id ", referencedColumnName = "id")
    private OpportunityPaymentTerm opportunityPaymentTerm;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
