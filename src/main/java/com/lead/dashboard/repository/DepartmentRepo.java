package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
