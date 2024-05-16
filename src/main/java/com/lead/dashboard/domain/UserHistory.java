package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@Data
public class UserHistory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	
	@ManyToOne
	User user;
	
	Date currentDate;
	
	@ManyToOne
	User updatedBy;
}
