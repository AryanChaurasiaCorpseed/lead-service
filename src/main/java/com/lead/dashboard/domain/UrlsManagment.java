package com.lead.dashboard.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.lead.Lead;

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

@Table
@Entity
@Data
public class UrlsManagment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String urlsName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="url_slug",joinColumns = {@JoinColumn(name="urls_managment_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="url_slug_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Slug>urlSlug;
	boolean isQuality;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="url_similar_slug",joinColumns = {@JoinColumn(name="urls_managment_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="url_similar_slug_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<Slug>urlSimilarSlug;
	
	boolean isProduct;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrlsName() {
		return urlsName;
	}
	public void setUrlsName(String urlsName) {
		this.urlsName = urlsName;
	}
	public boolean isQuality() {
		return isQuality;
	}
	public void setQuality(boolean isQuality) {
		this.isQuality = isQuality;
	}
	public List<Slug> getUrlSlug() {
		return urlSlug;
	}
	public void setUrlSlug(List<Slug> urlSlug) {
		this.urlSlug = urlSlug;
	}
	public boolean isProduct() {
		return isProduct;
	}
	public void setProduct(boolean isProduct) {
		this.isProduct = isProduct;
	}
	public List<Slug> getUrlSimilarSlug() {
		return urlSimilarSlug;
	}
	public void setUrlSimilarSlug(List<Slug> urlSimilarSlug) {
		this.urlSimilarSlug = urlSimilarSlug;
	}
	
	
	  
	
}
