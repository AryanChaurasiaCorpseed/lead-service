package com.lead.dashboard.repository.VendorRepository;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.UserVendorRequestCount;
import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.domain.vendor.VendorSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserVendorRequestCountRepository extends JpaRepository<UserVendorRequestCount, Long> {

    Optional<UserVendorRequestCount> findByUserAndVendorCategoryAndVendorSubCategory(User user, VendorCategory vendorCategory, VendorSubCategory vendorSubCategory);
}
