package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.HrManagmentService;

@Service
public class HrManagmentServiceImpl implements HrManagmentService {

	@Autowired
	UserRepo userRepo;
	
	
	 
	
	@Override
	public List<User> getUserApprovalHr(Long userId) {
		   List<String> userRoles = userRepo.findRoleNameById(userId);
		   List<User> userList = new ArrayList<>();
		   if(userRoles.contains("HR_HEAD")||userRoles.contains("ADMIN")) {
				userList = userRepo.findAllByHrHeadApproivalAndIsDeleted(false,false);
		   }
		
		return userList;
	}



	@Override
	public Boolean approvedUserByHr(Long currentUserId, Long userId,boolean flag) {
		   List<String> userRoles = userRepo.findRoleNameById(userId);
		   if(userRoles.contains("HR_HEAD")||userRoles.contains("ADMIN")) {
			    User user = userRepo.findById(userId).get();
			    user.setHrHeadApproval(flag);
			    userRepo.save(user);
		   }

		return flag;
	}

}
