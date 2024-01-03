package com.lead.dashboard.controller.projectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.domain.PaymentData;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.dto.PaymentDataDto;
import com.lead.dashboard.dto.UpdateDataDto;
import com.lead.dashboard.service.PaymentDataService;
import com.lead.dashboard.util.UrlsMapping;

public class PaymentDataController {
	
	@Autowired
	PaymentDataService paymentDataService;
	
    @PostMapping(UrlsMapping.CREATE_PAYMENT_DATA)
    public ResponseEntity<PaymentData> createPaymentData(@RequestParam PaymentDataDto paymentDataDto) {
    	PaymentData payment = paymentDataService.createPaymentData(paymentDataDto);
        if (payment!=null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping(UrlsMapping.UPDATE_PAYMENT_DATA)
    public ResponseEntity<Boolean>updatePaymentData(@RequestParam UpdateDataDto updateDataDto) {
    	Boolean payment = paymentDataService.updatePaymentData(updateDataDto);
        if (payment) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @PostMapping(UrlsMapping.UPDATE_PAYMENT_DATA)
//    public ResponseEntity<PaymentData>updatePaymentData(@RequestParam PaymentDataDto paymentDataDto) {
//    	PaymentData payment = paymentDataService.updatePaymentData(paymentDataDto);
//        if (payment!=null) {
//            return ResponseEntity.ok(payment);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
