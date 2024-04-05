package com.lead.dashboard.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.HrManagmentService;

@Service
public class HrManagmentServiceImpl implements HrManagmentService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;
	
	 
	
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
			    User u=userRepo.save(user);
			    if(u.getManagers()!=null) {
			    	User manager = u.getManagers();
					String  feedbackStatusURLs = "http://98.70.36.18:3000/erp/setpassword/"+u.getId();   
					Context context1 = new Context();
					context1.setVariable("userName", "Aryan Chaurasia");
					context1.setVariable("user", u.getFullName());
					String subject="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
					context1.setVariable("email", u.getEmail());
					context1.setVariable("Rurl", feedbackStatusURLs);
					context1.setVariable("currentYear", LocalDateTime.now().getYear());
					String subject1="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
					String[] ccPerson= {u.getEmail()};
					String[] toMail= {manager.getEmail()};
					mailSendSerivceImpl.sendEmail(toMail, subject,"test",context1,"createUserManager.html");
				
			    }
		
		   }

		return flag;
	}

}
