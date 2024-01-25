package com.lead.dashboard.domain;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
//	@ManyToOne
//	User assignedBy;
	boolean isMissed;
	Long leadId;
	
	@ManyToOne
	TaskStatus TaskStatus;
	
//	@ManyToOne
//	User assigne;
	
	Date expectedDate;
	Date assignedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public TaskStatus getTaskStatus() {
		return TaskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		TaskStatus = taskStatus;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public boolean isMissed() {
		return isMissed;
	}
	public void setMissed(boolean isMissed) {
		this.isMissed = isMissed;
	}
	
	
	
	

}
