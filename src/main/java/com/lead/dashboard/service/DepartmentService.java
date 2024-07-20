package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Department;

@Service
public interface DepartmentService {

	List<Department> getAllDepartment();

	Department createDepartment(String name);

	 

}
