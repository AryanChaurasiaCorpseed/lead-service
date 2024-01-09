package com.lead.dashboard.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class SubNodes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	String name;
	
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="sub_node_child",joinColumns = {@JoinColumn(name="sub_node_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="sub_node_child_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
//    @JsonIgnore
	List<SuperSubNodes>subNodeChild;
    String url;
    String path;

    boolean isDeleted;

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

	public List<SuperSubNodes> getSubNodeChild() {
		return subNodeChild;
	}

	public void setSubNodeChild(List<SuperSubNodes> subNodeChild) {
		this.subNodeChild = subNodeChild;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
    
    
    
    
}
