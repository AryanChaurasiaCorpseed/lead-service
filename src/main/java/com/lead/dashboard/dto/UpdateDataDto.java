package com.lead.dashboard.dto;

import lombok.Data;

@Data
public class UpdateDataDto {

	Long paymentDataId;
	Long userId;
	String transactionId;
	Long profFees;
	Long govFees;
	Long ServiceFees;
	Long otherFees;
	Long totalPayment;
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
