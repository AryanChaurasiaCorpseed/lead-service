package com.lead.dashboard.domain.vendor;

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
    private VendorCategory vendorCategory;

    @ManyToMany
    @JoinTable(name = "vendor_subcategory_users",joinColumns = @JoinColumn (name = "vendor_subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> assignedUsers;

}