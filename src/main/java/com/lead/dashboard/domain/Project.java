package com.lead.dashboard.domain;

import java.util.Date;

import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Data
public class Project {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id ; 
	
	String name;
	
	String ProjectNo;
	
	int amount;
	
	String status;
	
	@ManyToOne
	ServiceDetails serviceDetails;
	
	@ManyToOne
	User assignee;
	
	@ManyToOne
	Product product;
	
	@ManyToOne
	Client client;
	
	@ManyToOne
	Company company;
	
	String progress;
	
	Date createDate;

	
	
//	@ManyToOne
//	Organization organization;
	
	// ------------- PAYMENT TYPE  --------------------------------------
	
	boolean isMileStone;
	boolean isFully;
	boolean isPartial;
	Long leadId;
	int docPercent;
	int fillingPercent;
	int liasPercent;
	int certPercent;
	int testPercent;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProjectNo() {
		return ProjectNo;
	}
	public void setProjectNo(String projectNo) {
		ProjectNo = projectNo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ServiceDetails getServiceDetails() {
		return serviceDetails;
	}
	public void setServiceDetails(ServiceDetails serviceDetails) {
		this.serviceDetails = serviceDetails;
	}
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isMileStone() {
		return isMileStone;
	}
	public void setMileStone(boolean isMileStone) {
		this.isMileStone = isMileStone;
	}
	public boolean isFully() {
		return isFully;
	}
	public void setFully(boolean isFully) {
		this.isFully = isFully;
	}
	public boolean isPartial() {
		return isPartial;
	}
	public void setPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}
	public int getDocPercent() {
		return docPercent;
	}
	public void setDocPercent(int docPercent) {
		this.docPercent = docPercent;
	}
	public int getFillingPercent() {
		return fillingPercent;
	}
	public void setFillingPercent(int fillingPercent) {
		this.fillingPercent = fillingPercent;
	}
	public int getLiasPercent() {
		return liasPercent;
	}
	public void setLiasPercent(int liasPercent) {
		this.liasPercent = liasPercent;
	}
	public int getCertPercent() {
		return certPercent;
	}
	public void setCertPercent(int certPercent) {
		this.certPercent = certPercent;
	}
	public int getTestPercent() {
		return testPercent;
	}
	public void setTestPercent(int testPercent) {
		this.testPercent = testPercent;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	
	
	

}
