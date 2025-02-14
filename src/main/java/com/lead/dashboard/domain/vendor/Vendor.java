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

	@Column(name = "requirement_description", length = 1000)
	private String requirementDescription;

	private String clientEmailId;

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

	@ManyToOne
	private User updatedBy;

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

	@ManyToOne
	private User viewedBy;

	private Date viewedAt;

	private Date cancelledAt;

	private Long cancelledBy;

	private boolean agreementStatus;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public List<String> getSalesAttachmentReferencePath() {
		return salesAttachmentReferencePath;
	}

	public void setSalesAttachmentReferencePath(List<String> salesAttachmentReferencePath) {
		this.salesAttachmentReferencePath = salesAttachmentReferencePath;
	}

	public List<String> getSalesAttachmentImage() {
		return salesAttachmentImage;
	}

	public void setSalesAttachmentImage(List<String> salesAttachmentImage) {
		this.salesAttachmentImage = salesAttachmentImage;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public String getRequirementDescription() {
		return requirementDescription;
	}

	public void setRequirementDescription(String requirementDescription) {
		this.requirementDescription = requirementDescription;
	}

	public String getClientEmailId() {
		return clientEmailId;
	}

	public void setClientEmailId(String clientEmailId) {
		this.clientEmailId = clientEmailId;
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

	public String getClientCompanyName() {
		return clientCompanyName;
	}

	public void setClientCompanyName(String clientCompanyName) {
		this.clientCompanyName = clientCompanyName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
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

	public String getClientBudget() {
		return clientBudget;
	}

	public void setClientBudget(String clientBudget) {
		this.clientBudget = clientBudget;
	}


	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isProposalSentStatus() {
		return proposalSentStatus;
	}

	public void setProposalSentStatus(boolean proposalSentStatus) {
		this.proposalSentStatus = proposalSentStatus;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public List<VendorUpdateHistory> getVendorUpdateHistory() {
		return vendorUpdateHistory;
	}

	public void setVendorUpdateHistory(List<VendorUpdateHistory> vendorUpdateHistory) {
		this.vendorUpdateHistory = vendorUpdateHistory;
	}

	public String getSharePriceToClient() {
		return sharePriceToClient;
	}

	public void setSharePriceToClient(String sharePriceToClient) {
		this.sharePriceToClient = sharePriceToClient;
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

	public User getViewedBy() {
		return viewedBy;
	}

	public void setViewedBy(User viewedBy) {
		this.viewedBy = viewedBy;
	}

	public Date getViewedAt() {
		return viewedAt;
	}

	public void setViewedAt(Date viewedAt) {
		this.viewedAt = viewedAt;
	}

	public Date getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	public Long getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(Long cancelledBy) {
		this.cancelledBy = cancelledBy;
	}






}