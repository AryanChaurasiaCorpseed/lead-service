package com.lead.dashboard.domain;

import com.lead.dashboard.domain.lead.Lead;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class TestMove {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id ; 
	
	String name;
}
