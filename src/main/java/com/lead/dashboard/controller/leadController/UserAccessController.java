package com.lead.dashboard.controller.leadController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.domain.Role;

import com.lead.dashboard.service.UserAccessService;
import com.lead.dashboard.util.UrlsMapping;

@Controller
public class UserAccessController {

	@Autowired
	UserAccessService userAccessService;

	@PostMapping(UrlsMapping.CREATE_USER_ACCESS)
	public 	Role createUserAccess(@RequestParam Long roleId) {
		
		
		return null;
	}
	@GetMapping(UrlsMapping.GET_USER_ACCESS)
	public List<Role>getUserAccess(){
		return null;
	}
	
	@GetMapping(UrlsMapping.GET_ALL_USER_ACCESS)
	public List<Role>getAllUserAccess(){
		return null;
	}
	
	@DeleteMapping(UrlsMapping.REMOVE_USER_ACCESS)
	public List<Role>removeUserAccess(){
		return null;
	}
	
	@PutMapping(UrlsMapping.UPDATE_USER_ACCESSE)
	public List<Role>updateUserAccess(){
		return null;
	}
	
	
}
