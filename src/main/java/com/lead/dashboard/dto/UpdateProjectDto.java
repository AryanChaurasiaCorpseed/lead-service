package com.lead.dashboard.dto;

import java.util.Date;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UpdateProjectDto {

	Long projectId;
	
    String name;
    
    Long updatedBy;
	
	String ProjectNo;
	
	String status;
	
	Long assigneeId;
	
	Long clientId;
	
	Long companyId;
	
	String progress;
	
	Date createDate;
	
}
