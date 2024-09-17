package com.lead.dashboard.domain.vendor;


import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_vendor")
    private User user;

    @ManyToOne
    @JoinColumn(name="lead_id")
    private Lead lead;

    private String requirementDescription;

    private String clientEmailId;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private UrlsManagment urlsManagment;

    private String clientCompanyName;

    private String contactPersonName;

    private String vendorReferenceFile;

    private boolean isDeleted;

    private boolean isDisplay;

    private String budgetPrice;

    private String vendorSharedPrice;

    private Long addedBy;

    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private String status;

    private boolean proposalSentStatus;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorUpdateHistory> vendorUpdateHistory;



}
