package com.lead.dashboard.domain.Organization;

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
	@ManyToOne
	Subscription subscription;// –  Testing
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
