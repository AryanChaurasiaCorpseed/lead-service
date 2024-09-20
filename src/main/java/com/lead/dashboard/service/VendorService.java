package com.lead.dashboard.service;

import com.lead.dashboard.dto.request.VendorEditRequest;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.VendorHistoryUpdate;
import com.lead.dashboard.dto.response.VendorResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorService {

    VendorResponse generateVendorRequest(VendorRequest vendorRequest , Long userId, Long leadId);

    List<VendorResponse> findVendorRequestsByUserId(Long userId ,Long leadId);


    VendorResponse updateVendorDetails(VendorEditRequest vendorEditRequest, Long vendorId, Long userId);

    VendorResponse sendQuotation(VendorQuotationRequest vendorQuotationRequest, Long userId, Long leadId, Long vendorRequestId);

    VendorHistoryUpdate updateVendorHistory(VendorRequestUpdate updateHistoryRequest, Long leadId, Long userId, Long vendorId);
}
