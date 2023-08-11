package com.lead.dashboard.controller.leadController;

import java.util.Date;

import com.lead.dashboard.domain.Contacts;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class LeadOpportunity {
	
	  @Id
      Long id;
	  
	  String Description;
	  Date estimateClose;
	  String confidence;
	  String value;
	  String onTime;
      Contacts contact;
	  
}
