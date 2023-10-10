package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;

	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}



	@Override
	public User createUser(UserDto user) {
		User u =new User();
		u.setId(user.getId());
		u.setFullName(user.getFullName());
		u.setEmail(user.getEmail());
		u.setDesignation(user.getDesignation());
		u.setDepartment(user.getDepartment());
		u.setRole(user.getRole());
		return userRepo.save(u);
	}

	@Override
	public User getUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User existingUser,UpdateUser user) {

		existingUser.setEmail(user.getEmail());
		existingUser.setDesignation(user.getDesignation());
		existingUser.setDepartment(user.getDepartment());
		existingUser.setRole(user.getRole());
		return userRepo.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public boolean isUserExistOrNot(Long userId) throws Exception {
		boolean flag=false;
		try {
			Optional<User> user = userRepo.findById(userId);
			if(user!=null && user.get()!=null) {
				flag=true;
			}
		}catch(Exception e) {
			throw new Exception("User Does not exist");
		}
		return flag;
	}
	public StringBuilder getRandomNumber() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 7;
		for(int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		return sb;
	}

	public boolean isUserEmailExistOrNot(String email) {
		boolean flag=false;
		User user = userRepo.findByemail(email);
		if(user!=null) {
			flag=true;
		}
		return flag;
	}



	@Override
	public User createUserByEmail(String userName, String email, String role, Long userId, String designation) {

		String[] emailTo= {"kaushlendra.pratap@corpseed.com"};
		String randomPass = getRandomNumber().toString();
		boolean isExistOrNot = isUserEmailExistOrNot(email);
		if(!isExistOrNot) {
			User u = new User();
			u.setId(userId);
			u.setFullName(userName);
			u.setEmail(email);
			List<String>listRole = new ArrayList();
			listRole.add(role);
			u.setRole(listRole);
			u.setDesignation(designation);
			String feedbackStatusURL = "https://corpseed.com" ;
			
					Context context = new Context();
					context.setVariable("userName", "Aryan Chaurasia");
					context.setVariable("email", email);
					context.setVariable("Rurl", feedbackStatusURL);
					context.setVariable("currentYear", LocalDateTime.now().getYear());
			String subject="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
			String text="CLICK ON THIS link and set password";
			userRepo.save(u);
			String[] ccPersons= {email};
//			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text);
			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"newUserCreate.html");
			return u;
		}else {
			User u = userRepo.findByemail(email);
			List<String>listRole = new ArrayList();
			listRole.add(role);
			u.setRole(listRole);
			String feedbackStatusURL = "http://localhost:3000/erp/login" ;
			
					Context context = new Context();
					context.setVariable("userName", "Aryan Chaurasia");
					context.setVariable("email", email);
					context.setVariable("Rurl", feedbackStatusURL);
					context.setVariable("currentYear", LocalDateTime.now().getYear());
			String subject="Corpseed pvt ltd send a request for adding on team please go and Accept";
			String text="CLICK ON THIS link and set password";
			userRepo.save(u);
			String[] ccPersons= {email};
//			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text);
			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"TeamAdd.html");

			return u;

		}
	}
//		 String randomPass = getRandomNumber().toString();
//			User u = new User();
//			System.out.println("11111111111111");
//
////		boolean isExistOrNot = isUserEmailExistOrNot(email);
//        if(true) {
//		u.setId(userId);
//		u.setEmail(email);
//		List<String>listRole = new ArrayList();
//		listRole.add(role);
//		u.setRole(listRole);
//		userRepo.save(u);
//		String feedbackStatusURL = "https://corpseed.com" ;
//
//		Context context = new Context();
//		context.setVariable("userName", "Aryan Chaurasia");
//		context.setVariable("email", email);
//		context.setVariable("Rurl", feedbackStatusURL);
//		context.setVariable("currentYear", LocalDateTime.now().getYear());
//		
//		String subject="Trade bee send a request for adding on team please go and Signup";
//		String text="CLICK ON THIS link and set password";
//		String[] ccPersons= {email};
//
//		mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"newUserCreate.html");
//
//        }else {
////        	if User exist
////   		 mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text);
//
//        }
//		return u;
//>>>>>>> 7cef39e90ce2b641760d5906429d3d0a0c985e86
//	}
}
