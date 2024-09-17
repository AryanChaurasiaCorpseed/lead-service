package com.lead.dashboard.domain.vendor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vendor_update_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @JsonIgnore
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name="user_vendor_history")
    private User user;

    @ManyToOne
    @JoinColumn(name="user_vendor")
    private User raiseTo;


    private String updateDescription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    @JsonIgnore
    private Lead lead;

    private String requestStatus;

    private boolean isDeleted;

    private boolean isDisplay;

    private String budgetPrice;

    private String vendorSharedPrice;

    private Long addedBy;

    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
}