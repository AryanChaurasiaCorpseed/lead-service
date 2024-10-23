package com.lead.dashboard.domain.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.ProductDocuments;
import com.lead.dashboard.domain.Stages;
import com.lead.dashboard.domain.User;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Date createdDate; 
    int  govermentfees;
    String govermentCode;
    String govermentGst;
    int professionalFees;
    String professionalCode;
    String profesionalGst;
    int serviceCharge;
    String serviceCode;
    String serviceGst;
    int otherFees;
    String otherCode;
    String otherGst;     
    @ManyToOne
    User createdBy;
    boolean isDeleted;
	List<String> documents;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="product_stage",joinColumns = {@JoinColumn(name="product_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="product_stage_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Stages>productStage;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="product_prod_documents",joinColumns = {@JoinColumn(name="product_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="product_prod_documents_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<ProductDocuments>productDoc;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	
	
	
	
	


	




    

}
