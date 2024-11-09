package com.lead.dashboard.service.vendorServices;

import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.VendorHistoryUpdated;
import com.lead.dashboard.dto.response.VendorResponse;

import java.util.List;
import java.util.Map;

public interface VendorService {

    VendorResponse generateVendorRequest(VendorRequest vendorRequest , Long userId, Long leadId);

    List<VendorResponse> findVendorRequestsByUserId(Long userId ,Long leadId);



    VendorResponse sendQuotation(VendorQuotationRequest vendorQuotationRequest, Long userId, Long leadId, Long vendorRequestId);

    VendorHistoryUpdated updateVendorHistory(VendorRequestUpdate updateHistoryRequest, Long leadId, Long userId, Long vendorId);


    List<VendorUpdateHistory> fetchUpdatedhistory(Long userId, Long leadId, Long vendorRequestId);

    List<VendorResponse> updateVendorDetails(List<Long> vendorId, Long updatedById, Long assigneeToId);

    Map<String, Object> findAllVendorRequest(Long userId, int page, int size);

    Map<String, Object> findAllVendorRequestOfUser(Long userId, int page, int size);

    boolean markVendorAsViewed(Long id, Long userId);

    boolean cancelRequest(Long vendorRequestId, Long userId, String cancelReason);
}
