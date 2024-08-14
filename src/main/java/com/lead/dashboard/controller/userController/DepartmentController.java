package com.lead.dashboard.controller.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Department;
import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.dto.DepartmentDto;
import com.lead.dashboard.dto.UpdateDepartment;
import com.lead.dashboard.repository.DepartmentRepo;
import com.lead.dashboard.service.DepartmentService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	
	

	
	@GetMapping(UrlsMapping.GET_ALL_DEPARTMENT)
	public ResponseEntity<List<Department>> getAllDepartment()
	{

		List<Department> allDepartment=departmentService.getAllDepartment();
		if(allDepartment!=null && allDepartment.size()!=0)
		{
			return  new ResponseEntity<>(allDepartment,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	

	@PostMapping(UrlsMapping.CREATE_DEPARTMENT)
	public ResponseEntity<Department> createDepartment(String name)
	{

		Department res=departmentService.createDepartment(name);
		if(res!=null)
		{
			return  new ResponseEntity<>(res,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	
	

	@PostMapping(UrlsMapping.CREATE_DEPARTMENT_IN_DESIGNATION)
	public ResponseEntity<Department> createDepartmentInDesignation(@RequestBody DepartmentDto depatmentDto)
	{

		Department res=departmentService.createDepartmentInDesignation(depatmentDto);
		if(res!=null)
		{
			return  new ResponseEntity<>(res,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	
	@GetMapping(UrlsMapping.GET_ALL_DESIGNATION_BY_DEPARTMENT)
	public ResponseEntity<List<Designation>> getAllDesignationByDepartment(@RequestParam Long departmentId)
	{

		List<Designation> allDesignation=departmentService.getAllDesignationByDepartment(departmentId);
		if(allDesignation!=null && allDesignation.size()!=0)
		{
			return  new ResponseEntity<>(allDesignation,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	
	@GetMapping(UrlsMapping.UPDATE_DEPARTMENT)
	public ResponseEntity<Boolean> updateDepartment(@RequestBody UpdateDepartment updateDepartment)
	{

		Boolean res=departmentService.updateDepartment(updateDepartment);
		if(res)
		{
			return  new ResponseEntity<>(res,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
	}
	
	
	
}
