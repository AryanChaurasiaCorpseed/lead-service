package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.PaymentData;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.dto.PaymentDataDto;
import com.lead.dashboard.dto.UpdateDataDto;

@Service
public interface PaymentDataService {

	PaymentData createPaymentData(PaymentDataDto paymentDataDto) throws Exception;

	Boolean updatePaymentData(UpdateDataDto updateDataDto);

	List<PaymentData> getAllPaymentData(Long userId);

	Boolean paymentApproved(Long userId, Long paymentId);

	Boolean paymentCancel(Long userId, Long paymentId);

	double gstCheck(double totalAmount, double percent);


}
