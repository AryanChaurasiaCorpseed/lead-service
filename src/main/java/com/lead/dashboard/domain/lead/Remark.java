package com.lead.dashboard.domain.lead;

import java.util.Date;
import java.util.List;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



//@Data
@Table
@Entity
public class Remark {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

	private String message;
	
	String images;
	

	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="remark_file_data",joinColumns = {@JoinColumn(name="remark_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="remark_file_data_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<FileData>imageList;
	
	String type;


	@ManyToOne
	User updatedBy;
	
	Date latestUpdated;
	
	boolean isDeleted;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getLatestUpdated() {
		return latestUpdated;
	}

	public void setLatestUpdated(Date latestUpdated) {
		this.latestUpdated = latestUpdated;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public List<FileData> getImageList() {
		return imageList;
	}

	public void setImageList(List<FileData> imageList) {
		this.imageList = imageList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
	

}
