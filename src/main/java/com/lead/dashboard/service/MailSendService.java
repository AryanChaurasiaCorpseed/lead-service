package com.lead.dashboard.service;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.request.VendorQuotationRequest;

public interface MailSendService {


    void sendEmailWithAttachmentForVendor(String[] emailTo, String[] ccPersons, String subject,
                                          String body, VendorQuotationRequest vendorQuotationRequest,
                                          User raisedBy);
}