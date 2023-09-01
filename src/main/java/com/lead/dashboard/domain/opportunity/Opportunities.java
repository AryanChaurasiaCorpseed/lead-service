package com.lead.dashboard.domain.opportunity;


import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.PaymentType;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Opportunities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    private String estimateClose;

    private String confidence;

    private String value;

    //In this how customer want to pay the estimate like full payment ,milestone or partial
//    @OneToMany
//    private List<PaymentType> paymentType;

//    @ManyToOne
//    private Lead lead;
//
//    @ManyToMany(mappedBy = "opportunities")
//    private List<Client> client;
////
//    @ManyToMany(mappedBy = "opportunities")
//    private List<User> user;

//    ERP user want to give some remark or want to provide some notes on it
    private String notes;


}
