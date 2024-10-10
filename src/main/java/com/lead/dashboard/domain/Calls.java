package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "calls")
@Data
@Getter
@Setter
public class Calls {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String mobileNo;
	String description;
}
