package com.lead.dashboard.domain.vendor;

import com.lead.dashboard.domain.lead.Lead;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "vendor_update_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private String updateDescription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne
    private Lead lead;

    private String requestStatus;

    private boolean isDeleted;

    private boolean isDisplay;

    private String estimatePrice;

    private String estimatePriceByVendorTeam;

    private Long addedBy;

    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;




}
