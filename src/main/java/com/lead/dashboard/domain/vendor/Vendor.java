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
@Table(name = "vendor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="created_by")
    private User user;

    @ManyToOne
    @JoinColumn(name="assigned_user")
    private User assignedUser;

    @ElementCollection
    @CollectionTable(name = "vendor_sales_attachments", joinColumns = @JoinColumn(name = "vendor_id"))
    @Column(name = "attachment_path")
    private List<String> salesAttachmentReferencePath;

    @ElementCollection
    @CollectionTable(name = "vendor_sales_images", joinColumns = @JoinColumn(name = "vendor_id"))
    @Column(name = "attachment_image")
    private List<String> salesAttachmentImage;

    @ManyToOne
    @JoinColumn(name="lead_id")
    @JsonIgnore
    private Lead lead;

    private String requirementDescription;

    private String clientEmailId;

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


    private String clientCompanyName;

    private String clientName;

    private String clientMobileNumber;


    private boolean isDeleted;

    private boolean isDisplay;

    private String clientBudget;

    private String vendorSharedDocument;

    private String vendorSharedPrice;

    private String vendorComment;

    private Long addedBy;

    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private String status;

    private boolean proposalSentStatus;

    boolean isView = false;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendorUpdateHistory> vendorUpdateHistory;

    private String sharePriceToClient;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "current_updated_date")
    private LocalDate currentUpdatedDate;

    private Long viewedBy;







}