package com.lead.dashboard.controller.vendorController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.response.VendorResponse;
import com.lead.dashboard.service.VendorService;
import com.lead.dashboard.util.UrlsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/leadService/api/v1/vendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @PostMapping(UrlsMapping.CREATE_VENDOR_REQUEST)
    public ResponseEntity<Object> createVendorRequest(@RequestBody VendorRequest vendorRequest, Long userId,@RequestParam MultipartFile files)
    {

        try {
            VendorResponse vendorResponse =   vendorService.generateVendorRequest(vendorRequest , userId, files);
            return new ResponseEntity<>(vendorResponse, HttpStatus.CREATED);

        } catch (Exception e) {

            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(UrlsMapping.FIND_VENDOr_REQUEST_BY_USER_ID)
    public ResponseEntity<Object> findVendorRequest(@RequestParam Long userId) {
        try {
            List<VendorResponse> vendorResponses = vendorService.findVendorRequestsByUserId(userId);
            return new ResponseEntity<>(vendorResponses, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
