package com.lead.dashboard.domain;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
/*
@Author :-Aryan Chaurasia

*/
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "task_managment")
public class TaskManagment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String name ;
	String description;
	
	@OneToOne
	User assignedBy;
	
	@OneToOne
	TaskStatus TaskStatus;
	
	@OneToOne
	User assigne;
	
	Date expectedDate;
	Date assignedDate;
	
	

}
