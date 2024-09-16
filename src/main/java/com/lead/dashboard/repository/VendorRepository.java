package com.lead.dashboard.repository;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository  extends JpaRepository<Vendor,Long> {


    List<Vendor> findAllVendorRequestByUserId(User user);
}
