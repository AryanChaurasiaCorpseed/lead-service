package com.lead.dashboard.domain.opportunity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "opportunity_pay_term")
public class OpportunityPaymentTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "pay_term_name")
    private String paymentTermName ;

    private String description;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String createdByName;

    private String updatedByName;

}
