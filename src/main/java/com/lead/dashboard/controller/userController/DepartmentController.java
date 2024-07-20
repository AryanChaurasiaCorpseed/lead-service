package com.lead.dashboard.controller.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Department;
import com.lead.dashboard.domain.Designation;
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
	
	
}
