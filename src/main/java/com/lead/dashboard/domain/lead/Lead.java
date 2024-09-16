package com.lead.dashboard.domain.lead;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.product.Product;

//import com.lead.dashboard.domain.opportunity.Opportunities;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "erp_leads")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
public class Lead {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

	private String uuid;

	//	Here we provide name of client who initiated enquiry from corpseed website
	@Column(name = "client_name")
	private String name;

	//	Name of lead like GST service,private company registration̥
	@NotBlank
	private String leadName;

	//Lead Description in which customer will fill there requirements
	private String leadDescription;

	//Client mobile number
	@Column(name = "client_mob_no")
	@NotBlank
	private String mobileNo;

	@Column
	private String email;

	//URL of website where client raise this enquiry
	private String urls ;

    private Date createDate ;
    
    @ManyToOne
    private User createdBy;

	private Date lastUpdated;

	private Date latestStatusChangeDate;

    //From which source with got this lead like IVR,WhatsApp,Website form Page
	private String source ;

	private String PrimaryAddress ;

	boolean isDeleted;

	@Column
	private String city;

	@Column
	private String categoryId;

	@Column
	private String serviceId;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name="lead_product",joinColumns = {@JoinColumn(name="lead_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="lead_product_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Product>products;

	@Column
	private String industryId;

	@Column
	private String ipAddress;

	private String displayStatus="1";

	private int whatsAppStatus;

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="lead_clients",joinColumns = {@JoinColumn(name="lead_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="lead_client_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Client>clients;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="lead_remark",joinColumns = {@JoinColumn(name="lead_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="lead_remark_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	@JsonIgnore
	List<Remark>remarks;

	@ManyToOne
	private User assignee;
	
	boolean isView;

	@ManyToOne
	private Status status;
	
	private boolean isMissedTask;
	private String missedTaskName;
	private Date missedTaskDate;
	private String missedTaskStatus;
	private String missedTaskCretedBy;
	private Boolean auto;
    private Boolean isUrlsChecked;  //when we assign a urls (Quality or Sales)
	private String originalName;
	private boolean isBacklogTask;
	private boolean  isNotAssignSame;
	private int count;
	
	private boolean isHelper;
	
	@ManyToOne
	private User helpUser;
	
	boolean isQualityWorked;

	@OneToMany(mappedBy = "lead", cascade = CascadeType.ALL)
	private List<Vendor> vendors;

//	@OneToMany
//	private List<Opportunities> opportunities;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLeadName() {
		return leadName;
	}

	public String getLeadDescription() {
		return leadDescription;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public String getUrls() {
		return urls;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public Date getLatestStatusChangeDate() {
		return latestStatusChangeDate;
	}

	public String getSource() {
		return source;
	}

	public String getPrimaryAddress() {
		return PrimaryAddress;
	}
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public String getCity() {
		return city;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public String getIndustryId() {
		return industryId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public int getWhatsAppStatus() {
		return whatsAppStatus;
	}

	public Status getStatus() {	
		return status;	
	}

	public String getUuid() {
		return uuid;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public void setLeadDescription(String leadDescription) {
		this.leadDescription = leadDescription;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setLatestStatusChangeDate(Date latestStatusChangeDate) {
		this.latestStatusChangeDate = latestStatusChangeDate;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setPrimaryAddress(String primaryAddress) {
		PrimaryAddress = primaryAddress;
	}

	
	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public void setWhatsAppStatus(int whatsAppStatus) {
		this.whatsAppStatus = whatsAppStatus;
	}

	public void setUuid(String uuid) {	this.uuid = uuid;	}

	public void setStatus(Status status) {		this.status = status;	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Remark> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<Remark> remarks) {
		this.remarks = remarks;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isMissedTask() {
		return isMissedTask;
	}

	public void setMissedTask(boolean isMissedTask) {
		this.isMissedTask = isMissedTask;
	}

	public String getMissedTaskName() {
		return missedTaskName;
	}

	public void setMissedTaskName(String missedTaskName) {
		this.missedTaskName = missedTaskName;
	}

	public Date getMissedTaskDate() {
		return missedTaskDate;
	}

	public void setMissedTaskDate(Date missedTaskDate) {
		this.missedTaskDate = missedTaskDate;
	}

	public String getMissedTaskStatus() {
		return missedTaskStatus;
	}

	public void setMissedTaskStatus(String missedTaskStatus) {
		this.missedTaskStatus = missedTaskStatus;
	}

	public String getMissedTaskCretedBy() {
		return missedTaskCretedBy;
	}

	public void setMissedTaskCretedBy(String missedTaskCretedBy) {
		this.missedTaskCretedBy = missedTaskCretedBy;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public Boolean getAuto() {
		return auto;
	}

	public void setAuto(Boolean auto) {
		this.auto = auto;
	}

	public Boolean getIsUrlsChecked() {
		return isUrlsChecked;
	}

	public void setIsUrlsChecked(Boolean isUrlsChecked) {
		this.isUrlsChecked = isUrlsChecked;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public boolean isBacklogTask() {
		return isBacklogTask;
	}

	public void setBacklogTask(boolean isBacklogTask) {
		this.isBacklogTask = isBacklogTask;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isNotAssignSame() {
		return isNotAssignSame;
	}

	public void setNotAssignSame(boolean isNotAssignSame) {
		this.isNotAssignSame = isNotAssignSame;
	}

	public boolean isHelper() {
		return isHelper;
	}

	public void setHelper(boolean isHelper) {
		this.isHelper = isHelper;
	}

	public User getHelpUser() {
		return helpUser;
	}

	public void setHelpUser(User helpUser) {
		this.helpUser = helpUser;
	}

	public boolean isQualityWorked() {
		return isQualityWorked;
	}

	public void setQualityWorked(boolean isQualityWorked) {
		this.isQualityWorked = isQualityWorked;
	}
	
	
	
	
}
