package com.lead.dashboard.domain.Organization;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.product.Category;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table
@Entity

public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name ;
	Long teamSize;
 	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="organization_roles",joinColumns = {@JoinColumn(name="organization_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="organization_role_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Role> roles;
	@ManyToOne
	Industry industry; 
	
	String industryName;
	
	// subscription data
	@ManyToOne
	Subscription subscription;// –  Testing
	String typePlan;
	@ManyToOne
	Plans subscribedPlans;
	Date plansStart;
	Date plansEnd;
	Boolean isPlanActiveOrNot;
	
	
	
 	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="organization_category",joinColumns = {@JoinColumn(name="organization_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="organization_category_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Category>organizationCategory;
	
 	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="organization_product",joinColumns = {@JoinColumn(name="organization_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="organization_product_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Product>organizationProduct;
 	
 	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="organization_status",joinColumns = {@JoinColumn(name="organization_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="organization_status_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Status>organizationStatus;
 	
 	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="organization_user_managment",joinColumns = {@JoinColumn(name="organization_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="organization_user_managment_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<UserManagment>organizationUserManagment;

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

	public Long getTeamSize() {
		return teamSize;
	}

	public void setTeamSize(Long teamSize) {
		this.teamSize = teamSize;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public String getTypePlan() {
		return typePlan;
	}

	public void setTypePlan(String typePlan) {
		this.typePlan = typePlan;
	}

	public Plans getSubscribedPlans() {
		return subscribedPlans;
	}

	public void setSubscribedPlans(Plans subscribedPlans) {
		this.subscribedPlans = subscribedPlans;
	}

	public Date getPlansStart() {
		return plansStart;
	}

	public void setPlansStart(Date plansStart) {
		this.plansStart = plansStart;
	}

	public Date getPlansEnd() {
		return plansEnd;
	}

	public void setPlansEnd(Date plansEnd) {
		this.plansEnd = plansEnd;
	}

	public Boolean getIsPlanActiveOrNot() {
		return isPlanActiveOrNot;
	}

	public void setIsPlanActiveOrNot(Boolean isPlanActiveOrNot) {
		this.isPlanActiveOrNot = isPlanActiveOrNot;
	}

	public List<Category> getOrganizationCategory() {
		return organizationCategory;
	}

	public void setOrganizationCategory(List<Category> organizationCategory) {
		this.organizationCategory = organizationCategory;
	}

	public List<Product> getOrganizationProduct() {
		return organizationProduct;
	}

	public void setOrganizationProduct(List<Product> organizationProduct) {
		this.organizationProduct = organizationProduct;
	}

	public List<Status> getOrganizationStatus() {
		return organizationStatus;
	}

	public void setOrganizationStatus(List<Status> organizationStatus) {
		this.organizationStatus = organizationStatus;
	}

	public List<UserManagment> getOrganizationUserManagment() {
		return organizationUserManagment;
	}

	public void setOrganizationUserManagment(List<UserManagment> organizationUserManagment) {
		this.organizationUserManagment = organizationUserManagment;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	
	
 	
    
//
//
//
 	
//	@ManyToMany
//	 UsersRole usersRole;
//	@One ToMany
//	Status status                       // Lead status
//	@One ToMany
//	 Category category; 
//	@One ToMany
//	Product product;   
//	@ManyToMany
//	  Node Node;   
//	@ManyToMany
//	Company company;
 	
 	
 	
 	
 	
 	
 	
 	

}
