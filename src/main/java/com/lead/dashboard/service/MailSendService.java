package com.lead.dashboard.service;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.domain.vendor.VendorSubCategory;
import com.lead.dashboard.dto.request.VendorQuotationRequest;

import java.util.Optional;

public interface MailSendService {

    void sendEmailWithAttachmentForVendor(String[] emailTo, String[] ccPersons, String subject,
                                          String body, VendorQuotationRequest vendorQuotationRequest,
                                          User mailSentBy, Optional<VendorCategory> vendorCategory, Optional<VendorSubCategory> vendorSubCategory);


}
