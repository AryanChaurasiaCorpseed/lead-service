package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.domain.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {

	
}
