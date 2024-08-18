package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@Data
public class SubIndustry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String name;
}
