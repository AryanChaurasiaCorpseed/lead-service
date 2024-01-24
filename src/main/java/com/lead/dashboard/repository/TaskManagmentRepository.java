package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.TaskManagment;

/*
@Author :-Aryan Chaurasia

*/

@Repository
public interface TaskManagmentRepository extends JpaRepository<TaskManagment, Long> {

	@Query(value = "SELECT * FROM task_managment tm WHERE tm.assigne_id=:assigneeId", nativeQuery = true)
	List<TaskManagment> findByAssigneeId(Long assigneeId);

	@Query(value = "SELECT * FROM task_managment tm WHERE tm.lead_id=:leadId", nativeQuery = true)
	List<TaskManagment> findAllByLeadId(Long leadId); 
	
	@Query(value = "SELECT * FROM dashlead.task_managment tm where tm.expected_date=:d and task_status_id=:statusId", nativeQuery = true)
	List<TaskManagment> findAllByExpectedDateAndTaskStatusId(String d,Long statusId); 

}
