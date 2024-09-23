package com.lead.dashboard.service;

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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorService {

    VendorResponse generateVendorRequest(VendorRequest vendorRequest , Long userId, Long leadId);

    List<VendorResponse> findVendorRequestsByUserId(Long userId ,Long leadId);


    VendorResponse updateVendorDetails(VendorEditRequest vendorEditRequest, Long vendorId, Long userId);

    VendorResponse sendQuotation(VendorQuotationRequest vendorQuotationRequest, Long userId, Long leadId, Long vendorRequestId);

    VendorHistoryUpdated updateVendorHistory(VendorRequestUpdate updateHistoryRequest, Long leadId, Long userId, Long vendorId);

    List<VendorAllResponse> findAllVendorRequest(Long userId, int page, int size);

    List<VendorUpdateHistory> fetchUpdatedhistory(Long userId, Long leadId, Long vendorRequestId);
}
