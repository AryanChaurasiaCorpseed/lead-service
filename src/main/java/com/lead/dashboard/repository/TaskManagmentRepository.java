package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.TaskManagment;

/*
@Author :-Aryan Chaurasia

*/

@Repository
public interface TaskManagmentRepository extends JpaRepository<TaskManagment, Long> { 

}
