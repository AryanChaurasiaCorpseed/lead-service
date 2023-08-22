package com.lead.dashboard.domain.lead;


import com.lead.dashboard.domain.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "status_logs")
public class LeadStatusChangeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;

//    @ManyToOne
//    @JoinColumn(name = "change_by_user")
//    private User changedByUser;

    private String changedByUser;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status newStatus;

    private LocalDateTime changeTime;

    public Long getId() {
        return id;
    }

    public Lead getLead() {
        return lead;
    }

    public String getChangedByUser() {
        return changedByUser;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }



//    =======================================================Setter=======================================


    public void setId(Long id) {
        this.id = id;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public void setChangedByUser(String changedByUser) {
        this.changedByUser = changedByUser;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }
}
