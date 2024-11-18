package com.lead.dashboard.domain.vendor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorCategoryName;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
    @Comment(value = "1 : Active, 0 : Inactive")
    private boolean isEnable;

    private LocalDate date;

    private Long addedBy;

    private Long updatedBy;

    private boolean isDeleted;

    @OneToMany(mappedBy = "vendorCategory", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<VendorSubCategory> vendorSubCategories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorCategoryName() {
		return vendorCategoryName;
	}

	public void setVendorCategoryName(String vendorCategoryName) {
		this.vendorCategoryName = vendorCategoryName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Long addedBy) {
		this.addedBy = addedBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<VendorSubCategory> getVendorSubCategories() {
		return vendorSubCategories;
	}

	public void setVendorSubCategories(List<VendorSubCategory> vendorSubCategories) {
		this.vendorSubCategories = vendorSubCategories;
	}
    
    
    



}
