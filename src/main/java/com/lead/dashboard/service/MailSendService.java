package com.lead.dashboard.service;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.request.VendorQuotationRequest;

public interface MailSendService {

    void sendEmailWithAttachmentForVendor(String[] emailTo, String[] ccPersons, User from, String subject, String body,
                                          String attachmentPath, VendorQuotationRequest vendorQuotationRequest, User raisedBy);
}