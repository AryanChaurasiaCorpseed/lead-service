package com.lead.dashboard.controller.leadController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.config.AccountFeignClient;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.service.AccountService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountFeignClient accountFeignClient;

	@GetMapping(UrlsMapping.TEST_ACCOUNT_FEIGN)
	public String testAccountFeign()
	{
		return accountFeignClient.test();
	}
	
	@PostMapping(UrlsMapping.CREATE_USER_ACCOUNT_MODULE)
	public User createUserAccountModule(@RequestBody UserDto user)
	{
		return accountFeignClient.createUserByLead(user);
	}
	
	@PostMapping(UrlsMapping.CREATE_ALL_ROLE_ACCOUNT_SIDE)
	public Boolean createAllRoleAtAccountSide()
	{
		return accountService.createAllRoleAtAccountSide();
	}
}
