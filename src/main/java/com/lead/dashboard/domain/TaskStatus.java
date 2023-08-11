package com.lead.dashboard.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class TaskStatus {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id;
    String name;
    String description;
    boolean isDeleted;
;
}
