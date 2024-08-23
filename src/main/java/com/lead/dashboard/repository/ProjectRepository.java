package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {

	@Query(value = "SELECT * FROM project p WHERE p.id in(:projectId)", nativeQuery = true)
	List<Project> findAllByIdIn(List<Long> projectId);

    Project findByProjectNo(String projectNumber);
}
