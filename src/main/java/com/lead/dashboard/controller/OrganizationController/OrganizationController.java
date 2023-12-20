package com.lead.dashboard.controller.OrganizationController;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Organization.Organization;
import com.lead.dashboard.dto.CreateOrganization;
import com.lead.dashboard.service.OrganizationService;
import com.lead.dashboard.util.CorpseedCalender;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class OrganizationController {
	
	@Autowired
	OrganizationService organizationService;
	
	
	@PostMapping(UrlsMapping.CREATE_ORGANIZATION)	
	public ResponseEntity<Organization> createOrganization(@RequestBody CreateOrganization createOrganization)
	{				
		Organization res = organizationService.createOrganization(createOrganization);

		if (res!=null) {
				return new ResponseEntity<>(res, HttpStatus.CREATED);		
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@GetMapping(UrlsMapping.GET_ALL_ORGANIZATION)	
	public void getOrganization()
	{				
		Date d = new Date();
		System.out.println("before adding ..  "+d);
		Date dm =CorpseedCalender.addDate(d, 21);
		System.out.println("before adding ..  "+dm);

//		System.out.println("new  Date according to Date"+d);
//		Calendar c = Calendar.getInstance();
//        c.setTime(d);
//		c.add(Calendar.DATE, 21);
//		System.out.println("new  Date2 according to Date2"+c.getTime());

		
//		Calendar cal = Calendar.getInstance();
//		System.out.println("Calender new Date"+cal);
//		Date actualDate = cal.getTime();
//		System.out.println("Calender new Date"+actualDate);

//		cal.add(Calendar.DATE, 21);
//		Date modifiedDate = cal.getTime();
//		System.out.println(modifiedDate); 
//		System.out.println("Calender after adding  Date"+modifiedDate);


	}
	
	
	@GetMapping(UrlsMapping.GET_ORGANIZATION)	
	public Organization getOrganization(@RequestParam Long orgId) {
		Organization res=organizationService.getOrganization(orgId);
		return res;
	}
	
	@GetMapping(UrlsMapping.CAN_ADD_NEW_USER)	
	public Boolean canAddNewUser(@RequestParam Long orgId) {
		Boolean res=organizationService.canAddNewUser(orgId);
		return res;
	}


}
