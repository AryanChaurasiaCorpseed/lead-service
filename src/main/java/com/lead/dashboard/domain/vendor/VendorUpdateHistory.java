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
import java.time.LocalDate;
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
    @JsonIgnore

    private User user;

    @ManyToOne
    @JoinColumn(name="raised_by")
    private User raisedBy;

//    @ManyToOne
//    @JoinColumn(name = "service_id")
//    private UrlsManagment urlsManagment;


//    @ManyToOne
//    @JoinColumn(name = "service_id")
//    private UrlsManagment urlsManagment;

    @ManyToOne
    @JoinColumn(name = "vendor_category_id")
    private VendorCategory vendorCategory;

    @ManyToOne
    @JoinColumn(name = "vendor_sub_category_id")
    private VendorSubCategory vendorSubCategory;

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


    private Long updatedBy;

    private List<String> mailTo;  // Keep as List<String>

    private List<String> mailCc;   // Change this from List<User> to List<String>
    private String updatedName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private String externalVendorPrice;

    private String externalVendorFilePath;

    private String internalVendorPrices;

    private String internalVendorFilePath;

    private String quotationAmount;

    private String quotationFilePath;

    private boolean proposalSentStatus;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "current_updated_date")
    private LocalDate currentUpdatedDate;

    private Date cancelledAt;

    private Long cancelledBy;









}