package com.lead.dashboard.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UserDto;


@Service
@FeignClient(name="ACCOUNT-SERVICE", url="http://localhost:9002")
public interface AccountFeignClient {
	
	
	//======================= USER API=============
	
	@GetMapping("accountService/api/v1/account/test")
	public String test();
	
	@PostMapping("accountService/api/v1//users/createUserByLead")
	public User createUserByLead(@RequestBody UserDto user); 
	
	@PostMapping("accountService/api/v1/roles/createAllRoleByLead")
	public Boolean createAllRoleAtAccountSide(@RequestBody List<Role>roleList); 

}
