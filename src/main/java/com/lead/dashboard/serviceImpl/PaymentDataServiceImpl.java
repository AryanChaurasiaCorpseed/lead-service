package com.lead.dashboard.serviceImpl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.PaymentData;
import com.lead.dashboard.dto.PaymentDataDto;
import com.lead.dashboard.repository.PaymentDataRepo;
import com.lead.dashboard.service.PaymentDataService;

@Service
public class PaymentDataServiceImpl implements PaymentDataService{

	@Autowired
	PaymentDataRepo paymentDataRepo;
	@Override
	public PaymentData createPaymentData(PaymentDataDto paymentDataDto) {
	    // milestone percentage are started by
		PaymentData paymentData = new PaymentData();
		paymentData.setTransactionId(paymentDataDto.getTransactionId());
		paymentData.setProfFees(paymentDataDto.getProfFees());
		paymentData.setGovFees(paymentDataDto.getGovFees());
		paymentData.setServiceFees(paymentDataDto.getServiceFees());
		paymentData.setOtherFees(paymentDataDto.getOtherFees());
		paymentData.setTotalPayment(paymentDataDto.getTotalPayment());
		paymentData.setType(paymentDataDto.getType());
		paymentData.setMileStone(paymentDataDto.isMileStone());
		paymentData.setFully(paymentDataDto.isFully());
		paymentData.setPartial(paymentDataDto.isPartial());
		paymentData.setDocPercent(paymentDataDto.getDocPercent());
		paymentData.setFillingPercent(paymentDataDto.getFillingPercent());
		paymentData.setLiasPercent(paymentDataDto.getLiasPercent());
		paymentData.setCertPercent(100); //certificate Always 100
		paymentData.setTestPercent(paymentDataDto.getTestPercent());
		
		return paymentDataRepo.save(paymentData);
	}

}
