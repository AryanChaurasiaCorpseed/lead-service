package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {

	@Query(value = "SELECT * FROM project p WHERE p.id in(:projectId)", nativeQuery = true)
	List<Project> findAllByIdIn(List<Long> projectId);

     @Query(value = "SELECT * FROM project p WHERE p.project_no =:projectNo", nativeQuery = true)
	List<Project> findAllByProjectNo(String projectNo);

     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId", nativeQuery = true)
	 List<Project> findAllByAssigneeId(Long userId);
     
     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId", nativeQuery = true)
	 List<Project> findAllByAssigneeId(Long userId,Pageable pageable);

     @Query(value = "SELECT * FROM project p WHERE p.assignee_id =:userId and name =projectName", nativeQuery = true)
	 List<Project> findAllByAssigneeIdAndProjectName(Long userId, String projectName);

     @Query(value = "SELECT * FROM project p WHERE p.name =:projectName", nativeQuery = true)
	 List<Project> findAllByProjectName(String projectName);
	
}
