package com.lead.dashboard.domain;

import java.util.Date;

import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String transactionId;
	@ManyToOne
	Project project;
	Long profFees;
	Long govFees;
	Long ServiceFees;
	Long otherFees;
	Long totalPayment;
	String type; //  1.initiated , 2.verified
	
	
	
	

}
