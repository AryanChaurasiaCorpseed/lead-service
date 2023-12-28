package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Project;

@Repository
public interface ProjectRepository  extends JpaRepository<Project, Long> {

}
