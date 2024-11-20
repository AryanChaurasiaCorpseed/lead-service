package com.lead.dashboard.service.vendorServices;

import com.lead.dashboard.domain.vendor.VendorStatus;

import java.util.List;

public interface VendorStatusService {

    VendorStatus createVendorStatus(String vendorStatusName);

    List<VendorStatus> fetchAllVendorStatuses();

    void softDeleteVendorStatus(Long id);

    VendorStatus fetchVendorStatusById(Long id);
}
