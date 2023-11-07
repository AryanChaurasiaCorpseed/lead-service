package com.lead.dashboard.domain.opportunity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class OpportunityStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @Column(name = "status_name")
    private String name ;

    private String description;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private String createdByName;

    private String updatedByName;
    
    private boolean isDelete;


}
