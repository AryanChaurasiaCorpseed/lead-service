package com.lead.dashboard.controller.vendorController;


import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import com.lead.dashboard.dto.request.VendorEditRequest;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.VendorAllResponse;
import com.lead.dashboard.dto.response.VendorHistoryUpdated;
import com.lead.dashboard.dto.response.VendorHistoryUpdated;
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
            VendorHistoryUpdated response = vendorService.updateVendorHistory(updateHistoryRequest,leadId, userId, vendorId);
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

    @GetMapping(UrlsMapping.FIND_ALL_VENDOR_REQUEST)
    public ResponseEntity<Object> findVendorRequest(@RequestParam Long userId, @RequestParam int page, @RequestParam int size) {
        try {
            if (page < 0 || size <= 0) {
                return new ResponseEntity<>("Page index must not be less than zero and size must be greater than zero.", HttpStatus.BAD_REQUEST);
            }

            List<VendorAllResponse> vendorResponseDTOList = vendorService.findAllVendorRequest(userId, page, size);
            return new ResponseEntity<>(vendorResponseDTOList, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(UrlsMapping.FIND_UPDATE_REQUEST_HISTORY)
    public ResponseEntity<Object> findUpdateRequestHistory(@RequestParam Long userId, @RequestParam Long leadId,@RequestParam Long vendorRequestId) {
        try {

             List<VendorUpdateHistory> updateHistoryList = vendorService.fetchUpdatedhistory(userId,leadId,vendorRequestId);
            return new ResponseEntity<>(updateHistoryList, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
