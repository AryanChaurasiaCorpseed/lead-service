package com.lead.dashboard.repository;

import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorHistoryRepository extends JpaRepository<VendorUpdateHistory,Long> {

    List<VendorUpdateHistory> findByVendorIdAndIsDeletedFalse(Long id);
}
