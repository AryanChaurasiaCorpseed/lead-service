package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.vendor.VendorStatus;
import com.lead.dashboard.repository.VendorRepository.VendorStatusRepository;
import com.lead.dashboard.service.vendorServices.VendorStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class VendorStatusServiceImpl implements VendorStatusService {

    @Autowired
    private VendorStatusRepository vendorStatusRepository;


    @Override
    public VendorStatus createVendorStatus(String vendorStatusName) {
        VendorStatus vendorStatus = new VendorStatus();
        vendorStatus.setStatusName(vendorStatusName);
        vendorStatus.setCreateDate(new Date());
        vendorStatus.setUpdatedDate(new Date());
        vendorStatus.setDeleted(false);
        vendorStatus.setDisplay(true);
        vendorStatus.setDate(LocalDate.now());

        return vendorStatusRepository.save(vendorStatus);
    }
    @Override
    public List<VendorStatus> fetchAllVendorStatuses() {
        return vendorStatusRepository.findDisplayEnabledAndNonDeletedStatuses();
    }

    @Override
    public VendorStatus fetchVendorStatusById(Long id) {
        return vendorStatusRepository.findByIdAndIsDeletedFalseAndIsDisplayTrue(id)
                .orElseThrow(() -> new RuntimeException("Vendor status not found or is deleted"));
    }

    @Override
    public void softDeleteVendorStatus(Long id) {
        VendorStatus vendorStatus = vendorStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor status not found"));
        vendorStatus.setDeleted(true);
        vendorStatus.setUpdatedDate(new Date());
        vendorStatusRepository.save(vendorStatus);
    }

}

