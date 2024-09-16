package com.lead.dashboard.service;

import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.response.VendorResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VendorService {

    VendorResponse generateVendorRequest(VendorRequest vendorRequest , Long userId , MultipartFile files);


    List<VendorResponse> findVendorRequestsByUserId(Long userId);
}
