package com.lead.dashboard.domain.Organization;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@Data
public class Subscription {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	Plans plans;
	Date startDate;
	Date endDate;
	Boolean isActiveOrNot;  //– 
	Boolean isInternalOrg;
	
	@ManyToOne
	Invoice Invoice ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Plans getPlans() {
		return plans;
	}
	public void setPlans(Plans plans) {
		this.plans = plans;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Boolean getIsActiveOrNot() {
		return isActiveOrNot;
	}
	public void setIsActiveOrNot(Boolean isActiveOrNot) {
		this.isActiveOrNot = isActiveOrNot;
	}
	public Boolean getIsInternalOrg() {
		return isInternalOrg;
	}
	public void setIsInternalOrg(Boolean isInternalOrg) {
		this.isInternalOrg = isInternalOrg;
	}
	public Invoice getInvoice() {
		return Invoice;
	}
	public void setInvoice(Invoice invoice) {
		Invoice = invoice;
	}
	

}
