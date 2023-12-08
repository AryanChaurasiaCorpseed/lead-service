package com.lead.dashboard.controller.leadController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.repository.RoleRepository;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/securityService/api/v1")
public class RoleController {
	
	@Autowired
	RoleRepository roleRepository;
//	@Autowired
//	UserRepository userRepository;
//	  
//	@PatchMapping("/roles/updateUserRole")
//	public boolean updateUserRole(@RequestParam Long userId,List<String>role) {
//		Optional<User> optionalUser = userRepository.findById(userId);
//		if(optionalUser!=null) {
//			User user = optionalUser.get();
//			List<Role> roles = user.getRoles();
//		}
//		return true;
//	}
//	
//	@GetMapping("/roles/getUserRole")
//	public 	User getUserRole(@RequestParam Long userId) {
//		Optional<User> optionalUser = userRepository.findById(userId);
//		User user = optionalUser.get();	
//		return user;
//	}
	
	@PostMapping("/roles/createRole")
	public 	Role createRole(@RequestParam String name) {
		Role role = new Role();
		role.setName(name);
		role.setDeleted(false);
		Role r = roleRepository.save(role);
		return r;
	}
	@GetMapping("/roles/getRole")
	public List<Role>getAllRole(){
		List<Role> roles = roleRepository.findAll().stream().filter(i->!(i.isDeleted())).collect(Collectors.toList());
		return roles;
	}
	@GetMapping("/roles/updateRoleDummy")
	public 	Role  updateRoleDummy(@RequestParam Long roleId) {
		Role role = roleRepository.findById(roleId).get();   //SalesLeadscreate	SalesLeadsedit		SalesLeadsdelete	SalesLeadsupdate
		List<String>acccessList = Arrays.asList("Dashboard","HR","Sales","SalesLeads","SalesOpp","SalesLeadsget","SalesLeadsdelete","SalesLeadscreate","SalesLeadsupdate");		
		role.setAccessedNode(acccessList);
		roleRepository.save(role);
		return role;
	}
	
	@PostMapping("/roles/updateRole")
	public 	Role  updateRole(@RequestParam String name) {
		Role role = new Role();
		role.setName(name);
		role.setDeleted(false);
		Role r = roleRepository.save(role);
		return r;
	}
	

}
