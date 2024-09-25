package com.lead.dashboard.controller.userController;

import com.lead.dashboard.domain.CreateUserDto;
import com.lead.dashboard.domain.UpdateUserByHr;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.NewSignupRequest;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.dto.response.userResponse.ProcurementUserDTO;
import com.lead.dashboard.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("api/v1/users/getAllUser")
	public ResponseEntity<List<User>> getAllUserData()
	{

		List<User> allUser=userService.getAllUsers().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());;
		if(!allUser.isEmpty())
		{
			return  new ResponseEntity<>(allUser,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("api/v1/users/getAllUserByHierarchy")
	public ResponseEntity<List<User>> getAllUserByHierarchy(@RequestParam Long userId)
	{

		List<User> allUser=userService.getAllUserByHierarchy(userId);
		if(!allUser.isEmpty())
		{
			return  new ResponseEntity<>(allUser,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("api/v1/users/isUserExistOrNot")
	public boolean isUserExistOrNot(@RequestParam Long userId) throws Exception
	{

		boolean userExistOrNot=userService.isUserExistOrNot(userId);
		return userExistOrNot;
	}


	@PostMapping("api/v1/users/createUsser")
	public ResponseEntity<User> createUser(@RequestBody UserDto user) {
		User createdUser = userService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@GetMapping("api/v1/users/getUser")
	public ResponseEntity<User> getUserById(@RequestParam Long id) {
		User user = userService.getUserById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("api/v1/users/updateUserByPostman")
	public ResponseEntity<User> updateUserByPostman(@RequestBody UpdateUser user) {
		User existingUser = userService.getUserById(user.getId());
		if (existingUser != null) {
			User updatedUser = userService.updateUser(existingUser,user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("api/v1/users/deleteUser")
	public ResponseEntity<Boolean> deleteUser(@RequestParam Long id) {
		Boolean existingUser= userService.deleteUser(id);
		if (existingUser) {	
			return new ResponseEntity<>(existingUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("api/v1/users/createUserByEmail")
	public User createUserByEmail(@RequestBody NewSignupRequest newSignupRequest) {
		User createdUser = userService.createUserByEmail(newSignupRequest.getUserName(),newSignupRequest.getEmail(),newSignupRequest.getRole(),newSignupRequest.getId(),newSignupRequest.getDesignation(),newSignupRequest.getDepartment() ,newSignupRequest.getDesignationId(),newSignupRequest.getDepartmentId());
		return createdUser;
	}
	
	
	@PutMapping("api/v1/users/activateUser")
	public ResponseEntity<Boolean> activateUser(@RequestParam Long id) {
		Boolean existingUser= userService.createUser(id);
		if (existingUser) {	
			return new ResponseEntity<>(existingUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("api/v1/users/getAllDeactivateUser")
	public ResponseEntity<List<User>> getAllDeactivateUser()
	{

		List<User> allUser=userService.getAllUsers().stream().filter(i->i.isDeleted()==true).collect(Collectors.toList());;
		if(!allUser.isEmpty())
		{
			return  new ResponseEntity<>(allUser,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	
	
	@PutMapping("api/v1/users/updateUserData")
	public ResponseEntity<User> updateUserData(@RequestBody UpdateUser user) {
		User existingUser = userService.getUserById(user.getId());
		if (existingUser != null) {
			User updatedUser = userService.updateUserData(existingUser,user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("api/v1/users/createUserByHr")
	public User createUserByHr(@RequestBody CreateUserDto createUserDto) {
		User createdUser = userService.createUserByHr(createUserDto);
		return createdUser;
	}
	
	@PutMapping("api/v1/users/editUserByHr")
	public ResponseEntity<User> editUserByHr(@RequestBody UpdateUserByHr user) {
		User existingUser = userService.getUserById(user.getId());
		if (existingUser != null) {
			User updatedUser = userService.editUserByHr(existingUser,user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("api/v1/users/getUserForManager")
	public ResponseEntity<List<User>> getUserForManager(@RequestParam Long id) {
		List<User> user = userService.getUserForManager(id);
		if (user != null &&user.size()!=0) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@PutMapping("api/v1/users/approvedUserByManager")
	public ResponseEntity<Boolean> approvedUserByManager(@RequestParam Long currentUserId,@RequestParam Long userId,@RequestParam String status) {
		Boolean res = userService.approvedUserByManager(currentUserId,userId,status);
		if (res) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	@PutMapping("api/v1/users/autoActive")
	public ResponseEntity<Boolean> autoActive(@RequestParam Long userId,@RequestParam Long currentUser) {
		Boolean res = userService.autoActive(userId,currentUser);
		if (res) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	@GetMapping("api/v1/users/getUserManager")
	public ResponseEntity<List<Long>> getUserManager(@RequestParam Long id) {
		List<Long> user = userService.getUserManager(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("api/v1/users/updateLockerCount")
	public ResponseEntity<Boolean> updateLockerCount(@RequestParam Long id,@RequestParam int count,@RequestParam Long currentUserId) {
		Boolean existingUser= userService.updateLockerCount(id,count,currentUserId);
		if (existingUser) {	
			return new ResponseEntity<>(existingUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("api/v1/users/updateProfile")
	public Boolean updateProfile(@RequestParam Long userId,@RequestParam String profilePic) {
		Boolean createdUser = userService.updateProfile(userId,profilePic);
		return createdUser;
	}

	@GetMapping("api/v1/users/getProfile")
	public String getProfile(@RequestParam Long userId) {
		String createdUser = userService.getProfile(userId);
		return createdUser;
	}
	
	@GetMapping("api/v1/users/isManagerApproved")
	public ResponseEntity isManagerApproved(@RequestParam Long userId) {
		Boolean isManagerApproved = userService.isManagerApproved(userId);
		if(isManagerApproved)
		{
			return  new ResponseEntity<>(isManagerApproved,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(isManagerApproved,HttpStatus.OK);
		}
	}
	
	
	@GetMapping("api/v1/users/getSingleUserById")
	public Map<String,Object> getSingleUserById(@RequestParam Long userId)
	{

		Map<String,Object> userExistOrNot=userService.getSingleUserById(userId);
		return userExistOrNot;
	}
	@GetMapping("api/v1/users/getAllEmails")
	public List<String> getAllEmails()
	{

		List<String> allUserEmails=userService.getAllEmails();
		return allUserEmails;
	}
	
	@GetMapping("api/v1/users/checkEmailExist") 
	public Boolean checkEmailExist(@RequestParam String email)
	{

		List<User> allUserEmails=userService.checkEmailExist(email);
		if(allUserEmails!=null && allUserEmails.size()!=0)
		{
			return  true;
		}
		else {
			return false;
		}
	}
	
	@GetMapping("api/v1/users/getUserManagerByDepartment") 
	public List<User> getUserManagerByDepartment(@RequestParam Long departmentId)
	{

		List<User> allUser=userService.getUserManagerByDepartment(departmentId);
		return allUser;
	}


	@GetMapping("api/v1/users/fetchProcurementUsers")
	public ResponseEntity<List<ProcurementUserDTO>> getUserOfProcurement(@RequestParam Long userId) {

		List<User> userOfProcurement = userService.getUserOfProcurement(userId);

		List<ProcurementUserDTO> procurementUserDTOList = userOfProcurement.stream()
				.map(user -> new ProcurementUserDTO(
						user.getId(),
						user.getFullName(),
						user.getUserDesignation().getWeightValue()
				))
				.collect(Collectors.toList());

		if (!procurementUserDTOList.isEmpty()) {
			return new ResponseEntity<>(procurementUserDTOList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
