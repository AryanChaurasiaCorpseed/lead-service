package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
/*
@Author :-Aryan Chaurasia

*/

@Data
public class TaskManagment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String name ;
	String description;
	
	@OneToOne
	User assignedBy;
	
	@OneToOne
	User assigne;
	
	Date expectedDate;
	Date assignedDate;
	
	

}
