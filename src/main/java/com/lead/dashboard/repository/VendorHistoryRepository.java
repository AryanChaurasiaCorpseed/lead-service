package com.lead.dashboard.repository;

import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorHistoryRepository extends JpaRepository<VendorUpdateHistory,Long> {

    List<VendorUpdateHistory> findByVendorIdAndIsDeletedFalse(Long id);

    @Query(value = "SELECT * FROM vendor_update_history u WHERE u.lead_id = :leadId AND u.vendor_request_id = :vendorRequestId AND u.is_deleted = false",
            nativeQuery = true)
    List<VendorUpdateHistory> findUpdateHistoryByLeadAndVendor(@Param("leadId") Long leadId, @Param("vendorRequestId") Long vendorRequestId);}
