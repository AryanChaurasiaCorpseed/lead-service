package com.lead.dashboard.controller.vendorController;


import com.lead.dashboard.dto.request.VendorEditRequest;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.VendorHistoryUpdate;
import com.lead.dashboard.dto.response.VendorResponse;
import com.lead.dashboard.dto.response.VendorUpdateHistoryResponse;
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

    @PostMapping(value = UrlsMapping.CREATE_VENDOR_REQUEST)
    public ResponseEntity<Object> createVendorRequest(
                                                      @RequestParam Long leadId, @RequestParam Long userId,
                                                      @RequestBody VendorRequest vendorRequest) {

        try {
            VendorResponse vendorResponse = vendorService.generateVendorRequest(vendorRequest, userId, leadId);
            return new ResponseEntity<>(vendorResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(UrlsMapping.FIND_VENDOr_REQUEST_BY_USER_ID)
    public ResponseEntity<Object> findVendorRequest(@RequestParam Long userId,@RequestParam Long leadId) {
        try {
            List<VendorResponse> vendorResponses = vendorService.findVendorRequestsByUserId(userId,leadId);
            return new ResponseEntity<>(vendorResponses, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(UrlsMapping.UPDATE_VENDOR_REQUEST)
    public ResponseEntity<Object> updateVendorHistory(@RequestBody VendorRequestUpdate updateHistoryRequest,@RequestParam Long leadId,
                                                      @RequestParam Long userId, @RequestParam Long vendorId) {
        try {
            VendorHistoryUpdate response = vendorService.updateVendorHistory(updateHistoryRequest,leadId, userId, vendorId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping(UrlsMapping.UPDATE_VENDOR_DETAILS)
    public ResponseEntity<Object> updateVendorDetails(@RequestBody VendorEditRequest vendorEditRequest,
                                                      @RequestParam Long vendorId,
                                                      @RequestParam Long userId) {
        try {
            VendorResponse vendorResponse = vendorService.updateVendorDetails(vendorEditRequest, vendorId, userId);
            return new ResponseEntity<>(vendorResponse, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = UrlsMapping.SEND_QUOTATION_TO_CLIENT)
    public ResponseEntity<Object> sendQuotation(
            @RequestParam Long leadId, @RequestParam Long userId,Long vendorRequestId,
            @RequestBody VendorQuotationRequest vendorQuotationRequest) {

        try {
            VendorResponse vendorResponse = vendorService.sendQuotation(vendorQuotationRequest, userId, leadId,vendorRequestId);
            return new ResponseEntity<>(vendorResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
