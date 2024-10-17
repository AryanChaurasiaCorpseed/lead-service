package com.lead.dashboard.repository.VendorRepository;


import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor,Long> {


    @Query("SELECT v FROM Vendor v WHERE v.assignedUser.id = :userId AND v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findAllVendorRequestByAssignedUser(@Param("userId") Long userId, @Param("leadId") Long leadId);


    @Query("SELECT v FROM Vendor v WHERE v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findAllVendorRequests(@Param("leadId") Long leadId);


    @Query("SELECT v FROM Vendor v WHERE v.id = :vendorId")
    Vendor findVendorById(@Param("vendorId") Long vendorId);

    Page<Vendor> findAll(Pageable pageable);


    @Query("SELECT v FROM Vendor v JOIN v.user u WHERE u.id = :userId AND v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findVendorRequestsBySalesUserAndLead(@Param("userId") Long userId, @Param("leadId") Long leadId);

    Page<Vendor> findByAssignedUser(User assignedUser, Pageable pageable);

    Page<Vendor> findByUser(User user, Pageable pageable);
}
