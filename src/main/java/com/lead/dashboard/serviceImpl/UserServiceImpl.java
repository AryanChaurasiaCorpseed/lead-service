package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.CreateUserDto;
import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.UpdateUserByHr;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.UserRecord;
import com.lead.dashboard.dto.NewSignupRequest;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.repository.RoleRepository;
import com.lead.dashboard.repository.UserHistoryRepo;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.UserService;

import jakarta.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
	
    @Autowired
    UserHistoryRepo userHistoryRepo;

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
		u.setManagerApproval("approved");
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
            u.setManagerApproval("pending");

			List<Role> roleList = roleRepository.findAllByNameIn(listRole);
			u.setUserRole(roleList);
			u.setDesignation(designation);
			userRepo.save(u);
			String feedbackStatusURL = "https://erp.corpseed.com/erp/setpassword/"+u.getId();

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
            u.setManagerApproval("pending");

			u.setDepartment(department);
			String feedbackStatusURL = "https://erp.corpseed.com/erp/setpassword/"+u.getId();
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

//	   boolean isManager;
//		String epfNo;
//		String aadharCard;
//		String employeeId;	
//		@ManyToOne
//		User managerName;
//		int expInMonth;
//		int expInYear;
//	    Date dateOfJoining;
//	    String type;// internal ,external
	@Override
	public User editUserByHr(User existingUser, UpdateUserByHr user) {
		
		existingUser.setFullName(user.getFullName());
		existingUser.setEmail(user.getEmail());
		existingUser.setDesignation(user.getDesignation());
		existingUser.setDepartment(user.getDepartment());
		List<Role> roleList = roleRepository.findAllByNameIn(user.getRole());
		existingUser.setUserRole(roleList);
		existingUser.setRole(user.getRole());
		
		existingUser.setLockerSize(user.getLockerSize());
		existingUser.setBackupTeam(user.isBackupTeam());
		existingUser.setMaster(user.isBackupTeam());
		
		existingUser.setManager(user.isManager());
		existingUser.setEpfNo(user.getEpfNo());
		existingUser.setAadharCard(user.getAadharCard());
		existingUser.setEmployeeId(user.getEmployeeId());
	    existingUser.setExpInMonth(user.getExpInMonth());
	    existingUser.setExpInYear(user.getExpInYear());
	    existingUser.setDateOfJoining(user.getDateOfJoining());
	    existingUser.setType(user.getType());
	    
	    if(user.getManagerId()!=null &&user.getManagerId()!=0) {
			Optional<User> manager = userRepo.findById(user.getManagerId());
			existingUser.setManagers(manager!=null?manager.get():null);
	    }
	    existingUser.setFatherName(user.getFatherName());
	    existingUser.setFatherOccupation(user.getFatherOccupation());
	    existingUser.setFatherContactNo(user.getFatherContactNo());
	    existingUser.setMotherName(user.getMotherName());
	    existingUser.setMotherContactNo(user.getMotherContactNo());
	    existingUser.setMotherOccupation(user.getMotherOccupation());
	    existingUser.setSpouseName(user.getSpouseName());
	    existingUser.setSpouseContactNo(user.getSpouseContactNo());
	    existingUser.setNationality(user.getNationality());
	    existingUser.setLanguage(user.getLanguage());
	    existingUser.setEmergencyNumber(user.getEmergencyNumber());
	    existingUser.setPanNumber(user.getPanNumber());
	    existingUser.setPermanentAddress(user.getPermanentAddress());
	    existingUser.setResidentialAddress(user.getResidentialAddress());
		return userRepo.save(existingUser);
	}



	@Override
	public User createUserByHr(CreateUserDto createUserDto) {
		String[] emailTo= {"aryan.chaurasia@corpseed.com"};
		String randomPass = getRandomNumber().toString();
		boolean isExistOrNot = isUserEmailExistOrNot(createUserDto.getEmail());
		System.out.println(isExistOrNot);
        String managerEmail[]=new String[1];
		if(!isExistOrNot) {
			User u = new User();
			u.setId(createUserDto.getId());
			u.setFullName(createUserDto.getUserName());
			u.setEmail(createUserDto.getEmail());
            u.setDepartment(createUserDto.getDepartment());
			List<String>listRole = new ArrayList();		
			listRole.addAll(createUserDto.getRole());
			u.setRole(listRole);
            u.setLockerSize(createUserDto.getLockerSize());
            u.setBackupTeam(createUserDto.isBackupTeam());
            u.setMaster(createUserDto.isMaster());
			List<Role> roleList = roleRepository.findAllByNameIn(listRole);
			u.setUserRole(roleList);
			u.setDesignation(createUserDto.getDesignation());
			
			u.setManager(createUserDto.isManager());
			u.setEpfNo(createUserDto.getEpfNo());
			u.setAadharCard(createUserDto.getAadharCard());
			u.setEmployeeId(createUserDto.getEmployeeId());
		    u.setExpInMonth(createUserDto.getExpInMonth());
		    u.setExpInYear(createUserDto.getExpInYear());
		    u.setDateOfJoining(createUserDto.getDateOfJoining());
		    u.setType(createUserDto.getType());
		    
		    if(createUserDto.getManagerId()!=null) {
				Optional<User> managerOp = userRepo.findById(createUserDto.getManagerId());
				User manager = managerOp.get();
				u.setManagers(manager);
				managerEmail[0]=manager.getEmail();
		    }
		    u.setFatherName(createUserDto.getFatherName());
		    u.setFatherOccupation(createUserDto.getFatherOccupation());
		    u.setFatherContactNo(createUserDto.getFatherContactNo());
		    u.setMotherName(createUserDto.getMotherName());
		    u.setMotherContactNo(createUserDto.getMotherContactNo());
		    u.setMotherOccupation(createUserDto.getMotherOccupation());
		    u.setSpouseName(createUserDto.getSpouseName());
		    u.setSpouseContactNo(createUserDto.getSpouseContactNo());
		    u.setNationality(createUserDto.getNationality());
		    u.setLanguage(createUserDto.getLanguage());
		    u.setEmergencyNumber(createUserDto.getEmergencyNumber());
		    u.setPanNumber(createUserDto.getPanNumber());
		    u.setPermanentAddress(createUserDto.getPermanentAddress());
		    u.setResidentialAddress(createUserDto.getResidentialAddress());
            u.setManagerApproval("pending");
			
			userRepo.save(u);
			String feedbackStatusURL = "https://erp.corpseed.com/erp/setpassword/"+u.getId();

			Context context = new Context();
			context.setVariable("userName", "Aryan Chaurasia");
			context.setVariable("user", u.getFullName());

			context.setVariable("email", createUserDto.getEmail());
			context.setVariable("Rurl", feedbackStatusURL);
			context.setVariable("currentYear", LocalDateTime.now().getYear());
			String subject="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
			String text="CLICK ON THIS link and set password";
			String[] ccPersons= {createUserDto.getEmail()};
			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"newUserCreate.html");
	//==================use mail manager =============================
//			String feedbackStatusURLs = "http://98.70.36.18:3000/erp/setpassword/"+u.getId();
//
//			Context context1 = new Context();
//			context1.setVariable("userName", "Aryan Chaurasia");
//			context1.setVariable("user", u.getFullName());
//
//			context1.setVariable("email", createUserDto.getEmail());
//			context1.setVariable("Rurl", feedbackStatusURL);
//			context1.setVariable("currentYear", LocalDateTime.now().getYear());
//			String subject1="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
//			String[] ccPerson= {createUserDto.getEmail()};
//			mailSendSerivceImpl.sendEmail(managerEmail, ccPerson,ccPersons, subject,text,context,"createUserManager.html");
			
			
			return u;
		}else {
			User u = userRepo.findByemail(createUserDto.getEmail());
			List<String>listRole = new ArrayList();
			listRole.addAll(createUserDto.getRole());
			u.setRole(listRole);
			u.setDepartment(createUserDto.getDepartment());
			
			
			u.setManager(createUserDto.isManager());
			u.setEpfNo(createUserDto.getEpfNo());
			u.setAadharCard(createUserDto.getAadharCard());
			u.setEmployeeId(createUserDto.getEmployeeId());
		    u.setExpInMonth(createUserDto.getExpInMonth());
		    u.setExpInYear(createUserDto.getExpInYear());
		    u.setDateOfJoining(createUserDto.getDateOfJoining());
		    u.setType(createUserDto.getType());
		    
		    if(createUserDto.getManagerId()!=null) {
				Optional<User> managerOp = userRepo.findById(createUserDto.getManagerId());
				User manager = managerOp.get();
				u.setManagers(manager);
				managerEmail[0]=manager.getEmail();
		    }
		    u.setFatherName(createUserDto.getFatherName());
		    u.setFatherOccupation(createUserDto.getFatherOccupation());
		    u.setFatherContactNo(createUserDto.getFatherContactNo());
		    u.setMotherName(createUserDto.getMotherName());
		    u.setMotherContactNo(createUserDto.getMotherContactNo());
		    u.setMotherOccupation(createUserDto.getMotherOccupation());
		    u.setSpouseName(createUserDto.getSpouseName());
		    u.setSpouseContactNo(createUserDto.getSpouseContactNo());
		    u.setNationality(createUserDto.getNationality());
		    u.setLanguage(createUserDto.getLanguage());
		    u.setEmergencyNumber(createUserDto.getEmergencyNumber());
		    u.setPanNumber(createUserDto.getPanNumber());
		    u.setPermanentAddress(createUserDto.getPermanentAddress());
		    u.setResidentialAddress(createUserDto.getResidentialAddress());
            u.setManagerApproval("pending");

			
			String feedbackStatusURL = "https://erp.corpseed.com/erp/setpassword/"+u.getId();
			Context context = new Context();
			context.setVariable("userName", "Aryan Chaurasia");
			context.setVariable("email", createUserDto.getEmail());
			context.setVariable("Rurl", feedbackStatusURL);
			context.setVariable("currentYear", LocalDateTime.now().getYear());
			String subject="Corpseed pvt ltd send a request for adding on team please go and Accept";
			String text="CLICK ON THIS link and set password";
			userRepo.save(u);
			String[] ccPersons= {createUserDto.getEmail()};
			//			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text);
			mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"TeamAdd.html");

			return u;

		}
	}



	@Override
	public List<User> getUserForManager(Long id) {
		   List<User> userList = userRepo.findAllByManagerApprovedAndIsHrHeadApprovalAndIsDeleted(id,"pending",true,false);           
		return userList;
	}



	@Override
	public Boolean approvedUserByManager(Long currentUserId, Long userId, String status) {
		Boolean flag = false;
		 List<String> userRoles = userRepo.findRoleNameById(currentUserId);
		if(userRoles.contains("HR_HEAD")||userRoles.contains("ADMIN")) {
			 User user = userRepo.findById(userId).get();
			 user.setManagerApproval(status);
			 userRepo.save(user); 
			 flag=true;
			  
		}
		return flag;
	}
   
	public void createHistory(User user,User currentUser) {
		UserRecord userRecord = new UserRecord();
		userRecord.setCurrentUser(user);
		userRecord.setUpdatedBy(currentUser);
		userRecord.setEvent("user in-active");
		userHistoryRepo.save(userRecord);
		
	}

	@Override
	public Boolean autoActive(Long userId,Long currentUser) {
		Boolean flag=false;
		Optional<User> opUser = userRepo.findById(userId);
		Optional<User> opCurUser = userRepo.findById(currentUser);

		if(opUser!=null) {
			User user=opUser.get();
		    user.setAutoActive(!user.isAutoActive());
		    userRepo.save(user);
		    flag=true;
		    createHistory(user,opCurUser!=null?opCurUser.get():null);
		}
		return flag;
	}
	



	
}
