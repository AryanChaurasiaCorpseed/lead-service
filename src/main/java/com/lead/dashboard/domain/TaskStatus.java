package com.lead.dashboard.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "task_status")
public class TaskStatus {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id;
    String name;
    String description;
    boolean isDeleted;
;
}
