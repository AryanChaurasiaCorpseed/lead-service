package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Department;
import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.dto.DepartmentDto;
import com.lead.dashboard.dto.UpdateDepartment;
import com.lead.dashboard.repository.DepartmentRepo;
import com.lead.dashboard.repository.DesignationRepo;
import com.lead.dashboard.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	DesignationRepo designationRepo;

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

	@Override
	public Department createDepartmentInDesignation(DepartmentDto departmentDto) {
		Department department = departmentRepo.findById(departmentDto.getId()).get();
		List<Designation>designationList= designationRepo.findAllByIdIn(departmentDto.getDesignation());
		department.setDesignations(designationList);
		departmentRepo.save(department);
		return department;
	}

	@Override
	public List<Designation> getAllDesignationByDepartment(Long departmentId) {
		Department department = departmentRepo.findById(departmentId).get();
		List<Designation> designationList = department.getDesignations();
		return designationList;
	}

	@Override
	public Boolean updateDepartment(UpdateDepartment updateDepartment) {
		Boolean flag=false;
		Department department = departmentRepo.findById(updateDepartment.getId()).get();
		
		department.setName(updateDepartment.getName());
		
		List<Designation> designationList = designationRepo.findAllByIdIn(updateDepartment.getDesignationIds());
	
		department.setDesignations(designationList);
		updateDepartment.getWeightValue();
		department.setDeleted(false);
		departmentRepo.save(department);
		return null;
	}

}
