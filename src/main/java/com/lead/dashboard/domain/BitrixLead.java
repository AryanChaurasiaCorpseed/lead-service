package com.lead.dashboard.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bitrix_lead")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BitrixLead {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

	private String clientName;

	//	Name of lead like GST service,private company registration
	@NotBlank
	private String leadName;

	//Client mobile number
	@NotBlank
	private String mobileNo;

	@Column
	private String email;

	String assignee_person;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAssignee_person() {
		return assignee_person;
	}

	public void setAssignee_person(String assignee_person) {
		this.assignee_person = assignee_person;
	}
	

}
