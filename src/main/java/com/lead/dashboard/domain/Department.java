 package com.lead.dashboard.domain;

import java.util.List;

import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
@Table
public class Department {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
    private String name;
    
    boolean isDeleted;
    
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="department_designation",joinColumns = {@JoinColumn(name="department_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="department_designation_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Designation>designations;

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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Designation> getDesignations() {
		return designations;
	}

	public void setDesignations(List<Designation> designations) {
		this.designations = designations;
	}
        
    
    
    
}
