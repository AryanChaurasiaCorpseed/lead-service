package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class PaymentRegister {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	String transactionId;
	String serviceName;
	
	Long leadId;
	Long estimateId;
	
    int  govermentfees;
    String govermentCode;
    String govermentGst;
    int professionalFees;
    String professionalCode;
    String profesionalGst;
    int serviceCharge;
    String serviceCode;
    String serviceGst;
    int otherFees;
    String otherCode;
    String otherGst;

}
