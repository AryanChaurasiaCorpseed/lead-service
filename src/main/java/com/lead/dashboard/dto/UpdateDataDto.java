package com.lead.dashboard.dto;

import java.util.List;

import lombok.Data;

@Data
public class UpdateDataDto {

	Long paymentDataId;
	Long userId;
	List<String> transactionId;
	Long profFees;
	Long govFees;
	Long ServiceFees;
	Long otherFees;
	Long totalPayment;
    List<String>doc;
	String type; //  1.initiated , 2.verified
	Long serviceId;
	boolean isMileStone;
	boolean isFully;
	boolean isPartial;
	
	int docPercent;
	int fillingPercent;
	int liasPercent;
	int certPercent;
	int testPercent;
}
