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
	

}
