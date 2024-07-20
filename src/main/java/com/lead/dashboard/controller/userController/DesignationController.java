package com.lead.dashboard.controller.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lead.dashboard.service.DesignationService;
import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class DesignationController {
	
	@Autowired
	DesignationService DesignationService;

	
	@GetMapping(UrlsMapping.GET_ALL_DESIGNATION)
	public ResponseEntity<List<Designation>> getAllDesignation()
	{

		List<Designation> allDesignation=DesignationService.getAllDesignation();
		if(allDesignation!=null && allDesignation.size()!=0)
		{
			return  new ResponseEntity<>(allDesignation,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	
	@PostMapping(UrlsMapping.CREATE_DESIGNATION)
	public ResponseEntity<Designation> createDesignation(@RequestParam String name)
	{

		Designation allDesignation=DesignationService.createDesignation(name);
		if(allDesignation!=null)
		{
			return  new ResponseEntity<>(allDesignation,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
}
