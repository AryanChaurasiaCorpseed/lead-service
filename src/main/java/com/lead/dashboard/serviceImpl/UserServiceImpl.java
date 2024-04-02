package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.repository.RoleRepository;
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
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepo userRepo;

	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	@Autowired
	RoleRepository roleRepository;

	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}



	@Override
	public User createUser(UserDto user) {
		User u =new User();
		u.setId(user.getId());
		u.setFullName(user.getUsername());
		u.setEmail(user.getEmail());
		u.setDesignation(user.getDesignation());
		u.setDepartment(user.getDepartment());
		List<Role> roleList = roleRepository.findAllByNameIn(user.getRole());
		u.setUserRole(roleList);
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
		List<Role> roleList = roleRepository.findAllByNameIn(user.getRole());
		existingUser.setUserRole(roleList);
		existingUser.setRole(user.getRole());
		return userRepo.save(existingUser);
	}

	@Override
	public Boolean deleteUser(Long id) {
		Boolean flag=false;
		User user = userRepo.findById(id).get();
		user.setDeleted(true);
		userRepo.save(user);
		flag=true;
		return flag;
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
	public User createUserByEmail(String userName, String email, List<String> role, Long userId, String designation,String department) {

		String[] emailTo= {"aryan.chaurasia@corpseed.com"};
		String randomPass = getRandomNumber().toString();
		boolean isExistOrNot = isUserEmailExistOrNot(email);
		System.out.println(isExistOrNot);

		if(!isExistOrNot) {
			User u = new User();
			u.setId(userId);
			u.setFullName(userName);
			u.setEmail(email);
            u.setDepartment(department);
			List<String>listRole = new ArrayList();		
			listRole.addAll(role);
			u.setRole(listRole);

			List<Role> roleList = roleRepository.findAllByNameIn(listRole);
			u.setUserRole(roleList);
			u.setDesignation(designation);
			userRepo.save(u);
			String feedbackStatusURL = "http://98.70.36.18:3000/erp/setpassword/"+u.getId();

			Context context = new Context();
			context.setVariable("userName", "Aryan Chaurasia");
			context.setVariable("user", u.getFullName());

			context.setVariable("email", email);
			context.setVariable("Rurl", feedbackStatusURL);
			context.setVariable("currentYear", LocalDateTime.now().getYear());
			String subject="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
			String text="CLICK ON THIS link and set password";
			String[] ccPersons= {email};
			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"newUserCreate.html");
			return u;
		}else {
			User u = userRepo.findByemail(email);
			List<String>listRole = new ArrayList();
			listRole.addAll(role);
			u.setRole(listRole);
			u.setDepartment(department);
			String feedbackStatusURL = "http://98.70.36.18:3000/erp/setpassword/"+u.getId();
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
	@Override
	public List<User> getAllUserByHierarchy(Long userId) {
		List<User>userList = new ArrayList();

		List<String> userRoles = userRepo.findRoleNameById(userId);
		if(userRoles.contains("ADMIN")) {
			userList=userRepo.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		}else {
			userList=userRepo.findAll().stream().filter(i->i.getRole().contains("ADMIN")).collect(Collectors.toList());
			userList=userList.stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());

		}

		return userList;
	}



	@Override
	public Boolean createUser(Long id) {
		Boolean flag=false;
		Optional<User> opUser = userRepo.findById(id);
	    if(opUser!=null &&opUser.get()!=null) {
	    	User user = opUser.get();
	    	user.setDeleted(false);
	    	userRepo.save(user);
	    	flag=true;
	    }
		return flag;
	}



	@Override
	public User updateUserData(User existingUser, UpdateUser user) {
		existingUser.setFullName(user.getFullName());
		existingUser.setEmail(user.getEmail());
		existingUser.setDesignation(user.getDesignation());
		existingUser.setDepartment(user.getDepartment());
		List<Role> roleList = roleRepository.findAllByNameIn(user.getRole());
		existingUser.setUserRole(roleList);
		existingUser.setRole(user.getRole());
		return userRepo.save(existingUser);
	}

}
