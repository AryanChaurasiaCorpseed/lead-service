package com.lead.dashboard.service;

import com.lead.dashboard.dto.request.VendorEditRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.response.VendorResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorService {

    VendorResponse generateVendorRequest(VendorRequest vendorRequest , Long userId, Long leadId);

    List<VendorResponse> findVendorRequestsByUserId(Long userId ,Long leadId);

    VendorResponse updateVendorRequest(VendorRequest vendorRequest, Long vendorId, Long userId,Long leadId);

    VendorResponse updateVendorDetails(VendorEditRequest vendorEditRequest, Long vendorId, Long userId);
}
