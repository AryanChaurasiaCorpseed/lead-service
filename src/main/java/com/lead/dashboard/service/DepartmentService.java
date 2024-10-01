package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Department;
import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.dto.DepartmentDto;
import com.lead.dashboard.dto.UpdateDepartment;

@Service
public interface DepartmentService {

	List<Department> getAllDepartment();

	Department createDepartment(String name);

	Department createDepartmentInDesignation(DepartmentDto depatmentDto);

	List<Designation> getAllDesignationByDepartment(Long departmentId);

	Boolean updateDepartment(UpdateDepartment updateDepartment);

	 

}
