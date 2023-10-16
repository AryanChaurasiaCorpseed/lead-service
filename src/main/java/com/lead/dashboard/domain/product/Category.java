package com.lead.dashboard.domain.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.lead.dashboard.domain.Client;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CategoryName;

    private Date createdDate;
    
    
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="category_product",joinColumns = {@JoinColumn(name="category_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="category_product_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Product>products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	

//    @OneToMany(mappedBy = "category")
//    private List<Product> products = new ArrayList<>();
    
    


}
