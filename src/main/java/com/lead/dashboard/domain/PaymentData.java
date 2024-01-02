package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class PaymentData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String transactionId;
	Long profFees;
	Long govFees;
	Long ServiceFees;
	Long otherFees;
	Long totalPayment;
	String type; //  1.initiated , 2.verified
	
	boolean isMileStone;
	boolean isFully;
	boolean isPartial;
	
	int docPercent;
	int fillingPercent;
	int liasPercent;
	int certPercent;
	int testPercent;

}
