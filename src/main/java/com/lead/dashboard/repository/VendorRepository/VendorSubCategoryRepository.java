package com.lead.dashboard.repository.VendorRepository;

import com.lead.dashboard.domain.vendor.VendorSubCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorSubCategoryRepository extends JpaRepository<VendorSubCategory,Long> {

    @Query("SELECT vsc FROM VendorSubCategory vsc WHERE vsc.isEnable = true AND vsc.isDeleted = false")
    List<VendorSubCategory> findAllActive(Pageable pageable);

}
