package com.lead.dashboard.repository.VendorRepository;

import com.lead.dashboard.domain.vendor.VendorCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorCategoryRepo extends JpaRepository<VendorCategory,Long> {


    @Query("SELECT vc FROM VendorCategory vc WHERE vc.isEnable = true AND vc.isDeleted = false")
    List<VendorCategory> findEnabledAndNotDeleted(Pageable pageable);


//    Optional<Object> findByIdWithSubCategories(Long categoryId);

    @Query("SELECT vc FROM VendorCategory vc LEFT JOIN FETCH vc.vendorSubCategories WHERE vc.id = :categoryId")
    Optional<VendorCategory> findByIdWithSubCategories(@Param("categoryId") Long categoryId);

    @Query("SELECT vc.id, vc.vendorCategoryName, vsc.id, vsc.vendorSubCategoryName, vsc.vendorCategoryResearchTat, vsc.vendorCompletionTat, u.id, u.fullName, u.email " +
            "FROM VendorCategory vc " +
            "LEFT JOIN vc.vendorSubCategories vsc " +
            "LEFT JOIN vsc.assignedUsers u " +
            "WHERE vc.id = :categoryId")
    List<Object[]> findCategoryWithSubCategoriesAndUsers(@Param("categoryId") Long categoryId);


    @Query("SELECT vc FROM VendorCategory vc LEFT JOIN FETCH vc.vendorSubCategories WHERE vc.id = :categoryId")
    Optional<VendorCategory> findVendorCategoryWithSubCategories(@Param("categoryId") Long categoryId);


}
