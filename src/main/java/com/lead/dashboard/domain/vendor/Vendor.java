package com.lead.dashboard.domain.vendor;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    //person who is raise request to vendor team for service credits
    @ManyToOne
    @JoinColumn(name="user_vendor")
    private User user;

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

    private String estimatePrice;

    private String estimatePriceByVendorTeam;

    private Long addedBy;

    private Long updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    private String status;




}
