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

    @Column(length = 255)
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


    @ManyToOne
    private User updatedBy;

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

    @ManyToOne
    private User cancelledBy;

	private String agreementName;

	private String researchName;

	private String researchDocumentName;
    public void setUpdateDescription(String updateDescription) {
        if (updateDescription != null && updateDescription.length() > 255) {
            this.updateDescription = updateDescription.substring(0, 255);  // Truncate to 255 characters
        } else {
            this.updateDescription = updateDescription;
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(User raisedBy) {
		this.raisedBy = raisedBy;
	}

	public VendorCategory getVendorCategory() {
		return vendorCategory;
	}

	public void setVendorCategory(VendorCategory vendorCategory) {
		this.vendorCategory = vendorCategory;
	}

	public VendorSubCategory getVendorSubCategory() {
		return vendorSubCategory;
	}

	public void setVendorSubCategory(VendorSubCategory vendorSubCategory) {
		this.vendorSubCategory = vendorSubCategory;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isDisplay() {
		return isDisplay;
	}

	public void setDisplay(boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getBudgetPrice() {
		return budgetPrice;
	}

	public void setBudgetPrice(String budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<String> getMailTo() {
		return mailTo;
	}

	public void setMailTo(List<String> mailTo) {
		this.mailTo = mailTo;
	}

	public List<String> getMailCc() {
		return mailCc;
	}

	public void setMailCc(List<String> mailCc) {
		this.mailCc = mailCc;
	}

	public String getUpdatedName() {
		return updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getExternalVendorPrice() {
		return externalVendorPrice;
	}

	public void setExternalVendorPrice(String externalVendorPrice) {
		this.externalVendorPrice = externalVendorPrice;
	}

	public String getExternalVendorFilePath() {
		return externalVendorFilePath;
	}

	public void setExternalVendorFilePath(String externalVendorFilePath) {
		this.externalVendorFilePath = externalVendorFilePath;
	}

	public String getInternalVendorPrices() {
		return internalVendorPrices;
	}

	public void setInternalVendorPrices(String internalVendorPrices) {
		this.internalVendorPrices = internalVendorPrices;
	}

	public String getInternalVendorFilePath() {
		return internalVendorFilePath;
	}

	public void setInternalVendorFilePath(String internalVendorFilePath) {
		this.internalVendorFilePath = internalVendorFilePath;
	}

	public String getQuotationAmount() {
		return quotationAmount;
	}

	public void setQuotationAmount(String quotationAmount) {
		this.quotationAmount = quotationAmount;
	}

	public String getQuotationFilePath() {
		return quotationFilePath;
	}

	public void setQuotationFilePath(String quotationFilePath) {
		this.quotationFilePath = quotationFilePath;
	}

	public boolean isProposalSentStatus() {
		return proposalSentStatus;
	}

	public void setProposalSentStatus(boolean proposalSentStatus) {
		this.proposalSentStatus = proposalSentStatus;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getCurrentUpdatedDate() {
		return currentUpdatedDate;
	}

	public void setCurrentUpdatedDate(LocalDate currentUpdatedDate) {
		this.currentUpdatedDate = currentUpdatedDate;
	}

	public Date getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	public User getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(User cancelledBy) {
		this.cancelledBy = cancelledBy;
	}


	public String getUpdateDescription() {
		return updateDescription;
	}
    
    



}