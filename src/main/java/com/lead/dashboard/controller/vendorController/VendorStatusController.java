package com.lead.dashboard.controller.vendorController;

import com.lead.dashboard.domain.vendor.VendorStatus;
import com.lead.dashboard.service.vendorServices.VendorStatusService;
import com.lead.dashboard.util.UrlsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class VendorStatusController {

    @Autowired
    private VendorStatusService vendorStatusService;

    @PostMapping(value = UrlsMapping.CREATE_VENDOR_STATUS)
    public ResponseEntity<Object> createVendorStatus(@RequestParam String vendorStatusName) {
        try {
            VendorStatus createdStatus = vendorStatusService.createVendorStatus(vendorStatusName);
            return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = UrlsMapping.FETCH_ALL_VENDOR_STATUS)
    public ResponseEntity<List<VendorStatus>> fetchAllVendorStatuses() {
        try {
            List<VendorStatus> statuses = vendorStatusService.fetchAllVendorStatuses();
            return new ResponseEntity<>(statuses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = UrlsMapping.FETCH_VENDOR_STATUS_BY_ID)
    public ResponseEntity<VendorStatus> fetchVendorStatusById(@RequestParam Long id) {
        try {
            VendorStatus status = vendorStatusService.fetchVendorStatusById(id);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = UrlsMapping.SOFT_DELETE_VENDOR_STATUS)
    public ResponseEntity<Object> softDeleteVendorStatus(@RequestParam Long id) {
        try {
            vendorStatusService.softDeleteVendorStatus(id);
            return new ResponseEntity<>("Vendor status soft deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
