package com.lead.dashboard.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class ProductDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	
//	@ManyToOne
//	Product product;
}
