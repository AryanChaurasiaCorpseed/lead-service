package com.lead.dashboard.controller.vendorController;


import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorReportRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.VendorHistoryUpdated;
import com.lead.dashboard.dto.response.VendorResponse;
import com.lead.dashboard.service.vendorServices.VendorService;
import com.lead.dashboard.util.UrlsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

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
    public ResponseEntity<Object> updateVendorDetails(@RequestBody List<Long> vendorId,@RequestParam Long updatedById,@RequestParam Long assigneeToId) {
        try {
            List<VendorResponse> vendorResponseList = vendorService.updateVendorDetails(vendorId, updatedById,assigneeToId);
            return new ResponseEntity<>(vendorResponseList, HttpStatus.OK);
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

           Map<String,Object> vendorResponseDTOList = vendorService.findAllVendorRequest(userId, page, size);
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

    @GetMapping(UrlsMapping.FIND_ALL_VENDOR_REQUEST_OF_USER)
    public ResponseEntity<?> findAllVendorRequestOfUser(@RequestParam Long userId, @RequestParam int page, @RequestParam int size) {
        try {
            if (page < 0 || size <= 0) {
                return new ResponseEntity<>("Page index must not be less than zero and size must be greater than zero.", HttpStatus.BAD_REQUEST);
            }


            Map<String, Object> vendorRequestResponse = vendorService.findAllVendorRequestOfUser(userId, page, size);

            return new ResponseEntity<>(vendorRequestResponse, HttpStatus.OK);

        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(UrlsMapping.MARK_AS_VIEWED)
    public ResponseEntity<?> markVendorAsViewed(@RequestParam Long id, @RequestParam Long userId) {
        try {
            boolean updated = vendorService.markVendorAsViewed(id,userId);
            if (updated) {
                return new ResponseEntity<>("Vendor marked as viewed.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Vendor not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(UrlsMapping.CANCEL_VENDOR_REQUEST)
    public ResponseEntity<String> cancelRequest(@RequestParam Long vendorRequestId,
                                                @RequestParam Long userId,
                                                @RequestParam String cancelReason) {
        try {
            boolean updated = vendorService.cancelRequest(vendorRequestId, userId, cancelReason);

            if (updated) {
                // WebSocket notification
                String topic = "/topic/user/" + userId; // User-specific topic
                String notification = String.format("Your vendor request (ID: %d) has been canceled. Reason: %s",
                        vendorRequestId, cancelReason);
                messagingTemplate.convertAndSend(topic, notification);

                return new ResponseEntity<>("Vendor request canceled successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Vendor request not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(UrlsMapping.VENDOR_REPORT)
    public ResponseEntity<?> fetchVendorReport(@RequestBody VendorReportRequest vendorReportRequest) {
        try {
            if (vendorReportRequest.getStartDate() == null) {
                vendorReportRequest.setStartDate(LocalDate.now()); // Default to current date if null
            }
            if (vendorReportRequest.getEndDate() == null) {
                vendorReportRequest.setEndDate(LocalDate.now());
            }

            Map<String, Object> vendorRequestResponse = vendorService.fetchVendorReport(
                    vendorReportRequest.getUserIdBy(),
                    vendorReportRequest.getStatuses(),
                    vendorReportRequest.getStartDate(),
                    vendorReportRequest.getEndDate(),
                    vendorReportRequest.getUserIds()
            );

            return ResponseEntity.ok(vendorRequestResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




    @GetMapping(UrlsMapping.VENDOR_SEARCH)
    public ResponseEntity<?> searchVendorDetails(@RequestParam Long userId,@RequestParam String searchInput) {
        try {
            Map<String,Object> vendors = vendorService.searchVendors(userId,searchInput);
            return new ResponseEntity<>(vendors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
