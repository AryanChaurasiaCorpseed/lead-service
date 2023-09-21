package com.lead.dashboard.domain;

import java.util.List;

import com.lead.dashboard.domain.opportunity.Opportunities;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "service_details")
@Data
@Getter
@Setter
public class ServiceDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	private String name;
	@NotBlank
	private String leadName;
	String estimateData;
	
//	User clientAdmin;
	String company;
	String contact;
	Boolean consultingSale;
	String productType;
	String orderNumber;
	String purchaseDate;
	List<String>cc;
	String  invoiceNote;
	String remarksForOption;

	@ManyToOne
	Opportunities opportunities;
}
