package com.lead.dashboard.repository.VendorRepository;

import com.lead.dashboard.domain.vendor.VendorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorStatusRepository extends JpaRepository<VendorStatus,Long> {

    Optional<VendorStatus> findByIdAndIsDeletedFalseAndIsDisplayTrue(Long id);

    @Query("SELECT v FROM VendorStatus v WHERE v.isDisplay = true AND v.isDeleted = false")
    List<VendorStatus> findDisplayEnabledAndNonDeletedStatuses();
}
