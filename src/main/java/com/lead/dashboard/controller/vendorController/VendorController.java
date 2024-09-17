package com.lead.dashboard.controller.vendorController;


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

    @PostMapping(value = UrlsMapping.CREATE_VENDOR_REQUEST, consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createVendorRequest(@RequestParam(name = "file", required = false) MultipartFile files,
                                                      @RequestParam Long leadId, @RequestParam Long userId,
                                                      @RequestParam String description,
                                                      @RequestParam String concernPersonMailId,
                                                      @RequestParam Long urlId,
                                                      @RequestParam String companyName,
                                                      @RequestParam String contactPersonName,
                                                      @RequestParam String vendorReferenceFile) {

        try {
            VendorRequest vendorRequest = new VendorRequest(
                    description,
                    concernPersonMailId,
                    urlId,
                    companyName,
                    contactPersonName,
                    vendorReferenceFile
            );

            VendorResponse vendorResponse = vendorService.generateVendorRequest(vendorRequest, userId, files, leadId);
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

    @PutMapping(UrlsMapping.UPDATE_VENDOR_REQUEST)
    public ResponseEntity<Object> updateVendorRequest(@RequestBody VendorRequest vendorRequest, @RequestParam Long vendorId, Long userId,
                                                      @RequestParam(required = false) MultipartFile files) {
        try {
            VendorResponse vendorResponse = vendorService.updateVendorRequest(vendorRequest, vendorId, userId, files);
            return new ResponseEntity<>(vendorResponse, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
