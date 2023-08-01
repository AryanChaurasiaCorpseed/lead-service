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
@Table(name = "mails")
@Data
@Getter
@Setter
public class Mails {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String mailTo;
	String mailCc;
	String subject;
	String message ; 
	boolean isDeleted ; 
}
