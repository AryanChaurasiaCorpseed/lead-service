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

}
