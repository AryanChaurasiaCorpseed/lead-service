package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.config.AccountFeignClient;
import com.lead.dashboard.domain.Role;
import com.lead.dashboard.repository.RoleRepository;
import com.lead.dashboard.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	AccountFeignClient accountFeignClient;
	
	@Override
	public Boolean createAllRoleAtAccountSide() {
		// TODO Auto-generated method stub
		List<Role> role = roleRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		accountFeignClient.createAllRoleAtAccountSide(role);
		return true;
	}

}
