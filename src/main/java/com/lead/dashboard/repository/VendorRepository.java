package com.lead.dashboard.repository;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor,Long> {


    @Query("SELECT v FROM Vendor v WHERE v.user.id = :userId AND v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findAllVendorRequestByUserId(@Param("userId") Long userId, @Param("leadId") Long leadId);


    @Query("SELECT v FROM Vendor v WHERE v.lead.id = :leadId AND v.isDeleted = false")
    List<Vendor> findAllVendorRequests(@Param("leadId") Long leadId);



    @Query("SELECT v FROM Vendor v WHERE v.id = :vendorId")
    Vendor findVendorById(@Param("vendorId") Long vendorId);

}
