package com.lead.dashboard.controller.userController;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.NewSignupRequest;
import com.lead.dashboard.dto.UpdateUser;
import com.lead.dashboard.dto.UserDto;
import com.lead.dashboard.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

		List<User> allUser=userService.getAllUsers();
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

	@PutMapping("api/v1/users/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody UpdateUser user) {
		User existingUser = userService.getUserById(user.getId());
		if (existingUser != null) {
			User updatedUser = userService.updateUser(existingUser,user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("api/v1/users/deleteUser")
	public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
		User existingUser = userService.getUserById(id);
		if (existingUser != null) {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("api/v1/users/createUserByEmail")
	public User createUserByEmail(@RequestBody NewSignupRequest newSignupRequest) {
		User createdUser = userService.createUserByEmail(newSignupRequest.getUserName(),newSignupRequest.getEmail(),newSignupRequest.getRole(),newSignupRequest.getId(),newSignupRequest.getDesignation(),newSignupRequest.getDepartment());
		return createdUser;
	}
}
