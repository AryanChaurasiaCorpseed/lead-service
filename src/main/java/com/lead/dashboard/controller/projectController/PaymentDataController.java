package com.lead.dashboard.controller.projectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.PaymentData;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.dto.PaymentDataDto;
import com.lead.dashboard.dto.UpdateDataDto;
import com.lead.dashboard.service.PaymentDataService;
import com.lead.dashboard.util.UrlsMapping;
@RestController
public class PaymentDataController {
	
	@Autowired
	PaymentDataService paymentDataService;
	
    @PostMapping(UrlsMapping.CREATE_PAYMENT_DATA)
    public ResponseEntity<PaymentData> createPaymentData(@RequestBody PaymentDataDto paymentDataDto) throws Exception {
    	PaymentData payment = paymentDataService.createPaymentData(paymentDataDto);
        if (payment!=null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping(UrlsMapping.UPDATE_PAYMENT_DATA)
    public ResponseEntity<Boolean>updatePaymentData(@RequestBody UpdateDataDto updateDataDto) {
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
    
    @GetMapping(UrlsMapping.GET_ALL_PAYMENT_DATA)
    public ResponseEntity<List<PaymentData>>getAllPaymentData(@RequestParam Long userId) {
    	List<PaymentData> payment = paymentDataService.getAllPaymentData(userId);
        if (payment!=null &&payment.size()!=0) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(UrlsMapping.PAYMENT_APPROVED)
    public Boolean paymentApproved(Long userId,Long paymentId ) {
    	Boolean res = paymentDataService.paymentApproved(userId,paymentId);
    	return true;
    }
    
    
    @PutMapping(UrlsMapping.PAYMENT_CANCEL)
    public Boolean paymentCancel(Long userId,Long paymentId ) {
    	Boolean res = paymentDataService.paymentApproved(userId,paymentId);
    	return true;
    }
    
    @GetMapping(UrlsMapping.GST_CHECK)
    public double gstCheck(double totalAmount,double percent) {
    	double res = paymentDataService.gstCheck(totalAmount,percent);
    	return res;
    }
    
    

}
