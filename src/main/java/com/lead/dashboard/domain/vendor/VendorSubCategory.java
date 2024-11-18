package com.lead.dashboard.domain.vendor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lead.dashboard.domain.User;
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
public class VendorSubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorSubCategoryName;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(length = 1,name="is_enable",columnDefinition = "tinyint(1) default 1")
    @Comment(value = "1 : Active, 0 : Inactive")
    private boolean isEnable;

    private boolean isDeleted;

    private LocalDate date;

    private Long addedBy;

    private Long updatedBy;

    private int vendorCategoryResearchTat = 0;

    private int vendorCompletionTat =0;

    @ManyToOne
    @JoinColumn(name = "vendor_category_id")
    @JsonIgnore
    private VendorCategory vendorCategory;

    @ManyToMany
    @JoinTable(name = "vendor_subcategory_users",joinColumns = @JoinColumn (name = "vendor_subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private List<User> assignedUsers;

    private int lastAssignedUserIndex = -1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorSubCategoryName() {
		return vendorSubCategoryName;
	}

	public void setVendorSubCategoryName(String vendorSubCategoryName) {
		this.vendorSubCategoryName = vendorSubCategoryName;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public int getVendorCategoryResearchTat() {
		return vendorCategoryResearchTat;
	}

	public void setVendorCategoryResearchTat(int vendorCategoryResearchTat) {
		this.vendorCategoryResearchTat = vendorCategoryResearchTat;
	}

	public int getVendorCompletionTat() {
		return vendorCompletionTat;
	}

	public void setVendorCompletionTat(int vendorCompletionTat) {
		this.vendorCompletionTat = vendorCompletionTat;
	}

	public VendorCategory getVendorCategory() {
		return vendorCategory;
	}

	public void setVendorCategory(VendorCategory vendorCategory) {
		this.vendorCategory = vendorCategory;
	}

	public List<User> getAssignedUsers() {
		return assignedUsers;
	}

	public void setAssignedUsers(List<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}

	public int getLastAssignedUserIndex() {
		return lastAssignedUserIndex;
	}

	public void setLastAssignedUserIndex(int lastAssignedUserIndex) {
		this.lastAssignedUserIndex = lastAssignedUserIndex;
	}
    
    


}