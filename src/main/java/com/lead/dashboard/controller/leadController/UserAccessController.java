package com.lead.dashboard.controller.leadController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Role;

import com.lead.dashboard.service.UserAccessService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class UserAccessController {

	@Autowired
	UserAccessService userAccessService;

	@PostMapping(UrlsMapping.CREATE_USER_ACCESS)
	public 	Role createUserAccess(@RequestParam Long roleId) {
		boolean result=userAccessService.createUserAccess(roleId);
		
		return null;
	}
	@GetMapping(UrlsMapping.GET_USER_ACCESS)
	public List<Map<String,Object>>getUserAccess(@RequestParam Long roleId){
		List<Map<String,Object>> result=userAccessService.getUserAccess(roleId);
		

		return result;
	}
	
	@GetMapping(UrlsMapping.GET_SUBNODE_BY_NODE_AND_ROLE)
	public Map<String,Object>getSubNodeByNodeAndRole(@RequestParam Long roleId,@RequestParam Long nodeId){
	Map<String,Object> result=userAccessService.getSubNodeByNodeAndRole(roleId,nodeId);
		return result;
	}

	@GetMapping(UrlsMapping.GET_SUPER_SUBNODE_BY_SUBNODE)
	public Map<String,Object>getSuperSubNodeBySubNode(@RequestParam Long roleId,@RequestParam Long nodeId,@RequestParam Long subNodeId){
	Map<String,Object> result=userAccessService.getSuperSubNodeBySubNode(roleId,nodeId,subNodeId);
		return result;
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
