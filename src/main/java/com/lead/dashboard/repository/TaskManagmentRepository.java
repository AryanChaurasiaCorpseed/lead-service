package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.domain.lead.Lead;

/*
@Author :-Aryan Chaurasia

*/

@Repository
public interface TaskManagmentRepository extends JpaRepository<TaskManagment, Long> {

	@Query(value = "SELECT * FROM task_managment tm WHERE tm.assigned_by_id=:assigneeId", nativeQuery = true)
	List<TaskManagment> findByAssigneeId(Long assigneeId);

	@Query(value = "SELECT * FROM task_managment tm WHERE tm.lead_id=:leadId", nativeQuery = true)
	List<TaskManagment> findAllByLeadId(Long leadId); 
	
	@Query(value = "SELECT * FROM task_managment tm WHERE tm.lead_id=:leadId and task_status_id=:statusId and is_missed=true", nativeQuery = true)
	List<TaskManagment> findAllByLeadIdAndTaskStatusIdAndIsMissed(Long leadId,Long statusId); 
	
	@Query(value = "SELECT * FROM task_managment tm where tm.expected_date=:d and task_status_id=:statusId", nativeQuery = true)
	List<TaskManagment> findAllByExpectedDateAndTaskStatusId(String d,Long statusId);

	@Query(value = "SELECT * FROM task_managment tm where tm.is_missed =:b and tm.lead_id =:leadId", nativeQuery = true)
	List<Lead> findByIsMissedAndLeadId(boolean b, Long leadId); 
	@Query(value = "SELECT * FROM task_managment tm where tm.expected_date=:d", nativeQuery = true)
	List<TaskManagment> findAllByExpectedDateAndTaskStatusId(String d);
	
	@Query(value = "SELECT * FROM task_managment tm where tm.assigned_by_id=:assigneId and expected_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<TaskManagment> findAllByAssigneAndInExpectedDate(Long assigneId,String d1,String d2);
	
	
	
	
	

}
