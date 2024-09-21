package com.lead.dashboard.domain.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vendor_update_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorUpdateHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_request_id")
    @JsonIgnore
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name="current_assignee_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="raised_by")
    private User raisedBy;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private UrlsManagment urlsManagment;

    private String requestStatus;

    private String updateDescription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "lead_id")
    @JsonIgnore
    private Lead lead;

    private boolean isDeleted;

    private boolean isDisplay;

    private String budgetPrice;

    private String vendorSharedPrice;

    private Long updatedBy;

    private List<String> mailTo;

    private List<User> mailCs;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    private String quotationAttachmentPath;

    private String quotationAmount;


    private boolean proposalSentStatus;





}