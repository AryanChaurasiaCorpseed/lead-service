package com.lead.dashboard.repository;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor,Long> {


    @Query(value = "SELECT * FROM vendor v WHERE v.user_vendor = :userId AND v.is_deleted = false", nativeQuery = true)
    List<Vendor> findAllVendorRequestByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM vendor v WHERE v.is_deleted = false", nativeQuery = true)
    List<Vendor> findAllVendorRequests();

    @Query("SELECT v FROM Vendor v WHERE v.id = :vendorId")
    Vendor findVendorById(@Param("vendorId") Long vendorId);
}
