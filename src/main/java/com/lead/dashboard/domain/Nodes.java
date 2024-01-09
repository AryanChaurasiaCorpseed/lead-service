package com.lead.dashboard.domain;

import java.util.List;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.Data;

@Entity
@Table
@Data
public class Nodes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="node_child",joinColumns = {@JoinColumn(name="node_id",referencedColumnName="id",nullable=true)},
	inverseJoinColumns = {@JoinColumn(name="node_child_id"
			+ "",referencedColumnName = "id",nullable=true,unique=false)})
//    @JsonIgnore
	List<SubNodes>nodeChild;
	
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

	public List<SubNodes> getNodeChild() {
		return nodeChild;
	}

	public void setNodeChild(List<SubNodes> nodeChild) {
		this.nodeChild = nodeChild;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	


    

}
