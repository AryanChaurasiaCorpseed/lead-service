package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.Department;
import com.lead.dashboard.repository.DepartmentRepo;
import com.lead.dashboard.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	DepartmentRepo departmentRepo;

	@Override
	public List<Department> getAllDepartment() {
		List<Department> departmentList = departmentRepo.findAll();
		return departmentList;
	}

	@Override
	public Department createDepartment(String name) {
		Department d = new Department();
		d.setName(name);
		d.setDeleted(false);
		departmentRepo.save(d);
		return d;
	}

}
